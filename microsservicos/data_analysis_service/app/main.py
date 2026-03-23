import sys
import os
from datetime import datetime
from sqlalchemy import text
from app.services.auth import login
from app.services.equipamento_service import get_equipamentos
from app.services.execucao_service import get_execucoes
from app.services.medidor_service import get_medidores
from app.services.metrics.MetricaMTBF import MetricaMTBF
from app.services.ordem_service import get_ordens

sys.path.insert(0, os.path.abspath(os.path.dirname(__file__) + '/..'))

from app.database import init_db, SessionLocal
from app.models.schemas import OrdemManutencao, ExecucaoOrdem, Equipamento, Medidor


# --------------------------------------------------------------------
# Funções internas
# --------------------------------------------------------------------
def parse_datetime(date_str):
    """Converte string para datetime, retorna None se inválido"""
    if not date_str:
        return None
    for fmt in ["%Y-%m-%dT%H:%M:%S", "%Y-%m-%d %H:%M:%S", "%Y-%m-%d"]:
        try:
            return datetime.strptime(date_str, fmt)
        except:
            continue
    return None


# --------------------------------------------------------------------
# Funções para salvar dados no banco
# --------------------------------------------------------------------
def salvar_equipamentos(equipamentos, session):
    print("\n💾 Salvando equipamentos no banco...")
    ids = [str(e.get("id")) for e in equipamentos]
    existentes = set(row[0] for row in session.query(Equipamento.id).filter(Equipamento.id.in_(ids)).all())

    novos = ignorados = erros = 0

    for idx, e in enumerate(equipamentos, 1):
        try:
            equipamento_id = str(e.get("id"))
            if equipamento_id in existentes:
                ignorados += 1
                continue

            equipamento_db = Equipamento(
                id=equipamento_id,
                codigo=e.get("codigo"),
                nome=e.get("nome"),
                localizacao=e.get("localizacao"),
                numeroSerie=e.get("numeroSerie"),
                statusOperacional=e.get("statusOperacional"),
                criticidadeID=e.get("criticidadeID"),
            )
            session.add(equipamento_db)
            novos += 1

        except Exception as ex:
            erros += 1
            print(f"⚠️ Erro ao salvar equipamento {e.get('id')}: {ex}")

    session.commit()
    print(f"📊 RESUMO EQUIPAMENTOS: ✅ Novos: {novos}, ⏭️ Ignorados: {ignorados}, ⚠️ Erros: {erros}")


def salvar_medidores(medidores, session):
    print("\n💾 Salvando medidores no banco...")
    ids = [str(m.get("id")) for m in medidores]
    existentes = set(row[0] for row in session.query(Medidor.id).filter(Medidor.id.in_(ids)).all())

    novos = ignorados = erros = 0

    for m in medidores:
        try:
            medidor_id = str(m.get("id"))
            if medidor_id in existentes:
                ignorados += 1
                continue

            medidor_db = Medidor(
                id=medidor_id,
                equipamentoId=m.get("equipamentoId"),
                codigo=m.get("codigo"),
                nome=m.get("nome"),
                tipo=m.get("tipo"),
                unidade=m.get("unidade"),
                valorAtual=m.get("valorAtual"),
                valorMinimo=m.get("valorMinimo"),
                valorMaximo=m.get("valorMaximo"),
            )
            session.add(medidor_db)
            novos += 1

        except Exception as ex:
            erros += 1
            print(f"⚠️ Erro ao salvar medidor {m.get('id')}: {ex}")

    session.commit()
    print(f"📊 RESUMO MEDIDORES: ✅ Novos: {novos}, ⏭️ Ignorados: {ignorados}, ⚠️ Erros: {erros}")


def salvar_ordens(ordens, session):
    print("\n💾 Salvando ordens no banco...")
    ids_novos = [str(o.get("id")) for o in ordens]
    ids_existentes = set(row[0] for row in session.query(OrdemManutencao.id).filter(OrdemManutencao.id.in_(ids_novos)).all())

    novos = ignorados = erros = 0

    for o in ordens:
        try:
            ordem_id = str(o.get("id"))
            if ordem_id in ids_existentes:
                ignorados += 1
                continue

            ordem_db = OrdemManutencao(
                id=ordem_id,
                numeroOS=o.get("numeroOS"),
                planoManutencaoId=o.get("planoManutencaoID"),
                equipamentoId=o.get("equipamentoID"),
                tipoManutencao=o.get("tipoManutencao"),
                status=o.get("status"),
                prioridade=o.get("prioridade"),
                dataAbertura=parse_datetime(o.get("dataAbertura")),
                dataFechamento=parse_datetime(o.get("dataFechamento")),
                solicitanteID=o.get("solicitanteID"),
                responsavelID=o.get("responsavelID"),
                custoEstimado=o.get("custoEstimado"),
                custoReal=o.get("custoReal"),
                tempoParadaEstimado=o.get("tempoParadaEstimado"),
                tempoParadaReal=o.get("tempoParadaReal"),
                modoFalhaID=o.get("modoFalhaID"),
                causaRaizID=o.get("causaRaizID"),
            )
            session.add(ordem_db)
            novos += 1

        except Exception as e:
            erros += 1
            print(f"⚠️ Erro ao salvar ordem {o.get('id')}: {e}")

    session.commit()
    print(f"📊 RESUMO ORDENS: ✅ Novas: {novos}, ⏭️ Ignoradas: {ignorados}, ⚠️ Erros: {erros}")


def salvar_execucoes(execucoes, session):
    print("\n💾 Salvando execuções no banco...")
    novos = ignorados = erros = 0

    for e in execucoes:
        try:
            execucao_id = str(e.get("id"))
            existe = session.query(ExecucaoOrdem).filter(ExecucaoOrdem.id == execucao_id).first()
            if existe:
                ignorados += 1
                continue

            execucao_db = ExecucaoOrdem(
                id=execucao_id,
                ordemManutencaoId=e.get("ordemManutencaoID"),
                executorID=e.get("executorID"),
                dataInicio=parse_datetime(e.get("dataInicio")),
                dataFim=parse_datetime(e.get("dataFim")),
                periodosDeTrabalho=e.get("periodosDeTrabalho"),
                statusExecucao=e.get("statusExecucao"),
            )
            session.add(execucao_db)
            novos += 1

        except Exception as ex:
            erros += 1
            print(f"⚠️ Erro ao salvar execução {e.get('id')}: {ex}")

    session.commit()
    print(f"📊 RESUMO EXECUÇÕES: ✅ Novas: {novos}, ⏭️ Ignoradas: {ignorados}, ⚠️ Erros: {erros}")


# --------------------------------------------------------------------
# MAIN
# --------------------------------------------------------------------
def main():
    print("=" * 70)
    print("🚀 INICIANDO SINCRONIZAÇÃO DE DADOS")
    print("=" * 70)

    # Inicializar o banco de dados
    init_db()
    session = SessionLocal()

    try:
        # Login
        username, password = "ADMIN", "123"
        print("\n📍 ETAPA 1: Autenticação")
        jwt = login(username, password)

        # Buscar dados
        print("\n📍 ETAPA 2-5: Buscando dados")
        equipamentos = get_equipamentos(jwt)
        medidores = get_medidores(jwt)
        ordens = get_ordens(jwt)
        execucoes = get_execucoes(jwt)

        # Salvar no banco
        print("\n📍 ETAPA 6: Salvando dados no banco")
        if equipamentos: salvar_equipamentos(equipamentos, session)
        if medidores: salvar_medidores(medidores, session)
        if ordens: salvar_ordens(ordens, session)
        if execucoes: salvar_execucoes(execucoes, session)
        print("\n🎉 Todos os dados foram salvos com sucesso!")

            # Calcular MTBF
        print("\n📍 ETAPA 7: Calculando MTBF dos equipamentos")
        mtbf_calc = MetricaMTBF(session)
        equipamentos_ids = session.query(Equipamento.id).all()

        for eq_id_tuple in equipamentos_ids:
            equipamento_id = eq_id_tuple[0]
            mtbf, erro = mtbf_calc.calcular_mtbf(equipamento_id)

            if erro:
                print(f"⚠️ Não foi possível calcular MTBF do equipamento {equipamento_id}: {erro}")
                continue

            print(f"✅ MTBF do equipamento {equipamento_id}: {mtbf:.2f} horas")

            # 👉 GRAVA A MÉTRICA NO BANCO (Grafana vai ler daqui)
            session.execute(
                text("""
                    INSERT INTO mtbf_equipamento (equipamento_id, mtbf)
                    VALUES (:equipamento_id, :mtbf)
                """),
                {"equipamento_id": equipamento_id, "mtbf": mtbf}
            )
            session.commit()

    except Exception as e:
        print(f"❌ Erro durante a execução: {e}")
    finally:
        session.close()


if __name__ == "__main__":
    main()
