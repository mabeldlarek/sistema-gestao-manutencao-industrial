from app.models.schemas import Equipamento
from app.services.mtbf_service import MetricaMTBF  # ou onde você colocou a classe MTBF
from app.database import SessionLocal

if __name__ == "__main__":
    session = SessionLocal()

    mtbf_calc = MetricaMTBF(session)
    
    # Pega o primeiro equipamento cadastrado
    equipamento_id = session.query(Equipamento.id).first()[0]

    mtbf, erro = mtbf_calc.calcular_mtbf(equipamento_id)
    if erro:
        print(f"⚠️  Não foi possível calcular MTBF: {erro}")
    else:
        print(f"✅ MTBF do equipamento {equipamento_id}: {mtbf:.2f} horas")

    session.close()