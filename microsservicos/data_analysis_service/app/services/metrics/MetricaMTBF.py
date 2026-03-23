from sqlalchemy.orm import Session
from sqlalchemy import func
from prometheus_client import Gauge
from app.models.schemas import Equipamento, Medidor, OrdemManutencao
class MetricaMTBF:
    """
    Classe para calcular o MTBF (Mean Time Between Failures) de equipamentos
    e expor como métricas para Grafana via Prometheus.
    """

    # Gauge Prometheus para expor MTBF dos equipamentos
    mtbf_gauge = Gauge("mtbf_equipamento_horas", "MTBF em horas por equipamento", ["equipamento_id"])

    def __init__(self, session: Session):
        self.session = session

    def calcular_mtbf(self, equipamento_id: str):
        """
        Calcula o MTBF de um equipamento específico.
        MTBF = TEMPO DE FUNCIONAMENTO / NUMERO DE FALHAS CORRETIVAS
        """
        try:
            # 1️⃣ Buscar medidores do tipo TEMPO (horas) para o equipamento
            medidor_tempo = (
                self.session.query(Medidor)
                .filter(Medidor.equipamentoId == equipamento_id)
                .filter(Medidor.tipo == "TEMPO")  # ajuste conforme Enum
                .first()
            )
            if not medidor_tempo:
                return None, "Nenhum medidor de TEMPO encontrado"

            tempo_funcionamento = medidor_tempo.valorAtual  # horas

            # 2️⃣ Contar OMS corretivas associadas ao equipamento
            num_falhas = (
                self.session.query(OrdemManutencao)
                .filter(OrdemManutencao.equipamentoId== equipamento_id)
                .filter(OrdemManutencao.tipoManutencao == "CORRETIVA")
                .count()
            )

            if num_falhas == 0:
                return None, "Nenhuma falha corretiva registrada"

            # 3️⃣ Calcular MTBF
            mtbf = tempo_funcionamento / num_falhas

            # 4️⃣ Expor como métrica Prometheus
            self.mtbf_gauge.labels(equipamento_id=equipamento_id).set(mtbf)

            return mtbf, None

        except Exception as e:
            return None, str(e)