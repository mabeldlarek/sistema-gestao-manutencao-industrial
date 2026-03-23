import uuid
from datetime import datetime
from app.database import SessionLocal, init_db
from app.models.schemas import Equipamento, Medidor, OrdemManutencao, TipoMedidor, UnidadeMedida

def cadastrar_registros():
    init_db()  # cria tabelas
    session = SessionLocal()

    try:
        # Criar equipamento
        eq_id = str(uuid.uuid4())
        equipamento = Equipamento(
            id=eq_id,
            codigo="EQ-001",
            nome="Motor Principal",
            localizacao="Planta A",
            numeroSerie="SN-12345",
            statusOperacional="Operacional",
            criticidadeID="CRIT-1"
        )
        session.add(equipamento)

        # Criar medidor de tempo
        medidor = Medidor(
            id=str(uuid.uuid4()),
            equipamentoId=eq_id,
            codigo="TEMP-001",
            nome="Horímetro Motor",
            tipo=TipoMedidor.TEMPO,
            unidade=UnidadeMedida.HORAS,
            valorAtual=1200.0,  # Horas de funcionamento
            valorMinimo=0.0,
            valorMaximo=5000.0
        )
        session.add(medidor)

        # Criar ordens corretivas
        for i in range(3):  # 3 falhas corretivas
            ordem = OrdemManutencao(
                id=str(uuid.uuid4()),
                numeroOS=f"OM-00{i+1}",
                equipamentoId=eq_id,
                tipoManutencao="CORRETIVA",
                status="FINALIZADA",
                dataAbertura=datetime.now(),
                dataFechamento=datetime.now()
            )
            session.add(ordem)

        session.commit()
        print(f"✅ Cadastro concluído. Equipamento ID: {eq_id}")

    except Exception as e:
        session.rollback()
        print(f"❌ Erro ao cadastrar registros: {e}")
    finally:
        session.close()

if __name__ == "__main__":
    cadastrar_registros()
