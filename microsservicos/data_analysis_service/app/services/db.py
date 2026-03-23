from sqlalchemy import create_engine, Column, String, DateTime, Float, Integer, JSON
from sqlalchemy.orm import declarative_base, sessionmaker
import os

DATABASE_URL = os.getenv("DATABASE_URL", "postgresql://postgres:postgres@db:5432/ordens_db")

Base = declarative_base()
engine = create_engine(DATABASE_URL)
SessionLocal = sessionmaker(bind=engine)

class OrdemManutencao(Base):
    __tablename__ = "ordem_manutencao"
    id = Column(String, primary_key=True)
    numeroOS = Column(String, unique=True)
    planoManutencaoID = Column(String)
    equipamentoID = Column(String)
    descricaoProblema = Column(String)
    tipoManutencao = Column(String)
    status = Column(String)
    prioridade = Column(String)
    dataAbertura = Column(DateTime)
    dataFechamento = Column(DateTime)
    solicitanteID = Column(String)
    responsavelID = Column(String)
    procedimentoID = Column(String)
    observacoes = Column(String)
    custoEstimado = Column(Float)
    custoReal = Column(Float)
    tempoParadaEstimado = Column(Integer)
    tempoParadaReal = Column(Integer)
    modoFalhaID = Column(String)
    causaRaizID = Column(String)
    execucao = Column(JSON) 

def init_db():
    Base.metadata.create_all(bind=engine)
