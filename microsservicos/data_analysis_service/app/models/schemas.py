from sqlalchemy import Column, String, Float, BigInteger, DateTime, Enum, JSON
from sqlalchemy import ForeignKey
from sqlalchemy.orm import relationship
from app.database import Base
from datetime import datetime
import enum

# Enums
class TipoManutencao(str, enum.Enum):
    PREVENTIVA = "PREVENTIVA"
    CORRETIVA = "CORRETIVA"
    PREDITIVA = "PREDITIVA"

class StatusOrdem(str, enum.Enum):
    ABERTA = "ABERTA"
    EM_ANDAMENTO = "EM_ANDAMENTO"
    FINALIZADA = "FINALIZADA"
    CANCELADA = "CANCELADA"

class Prioridade(str, enum.Enum):
    BAIXA = "BAIXA"
    MEDIA = "MEDIA"
    ALTA = "ALTA"
    URGENTE = "URGENTE"

class StatusExecucao(str, enum.Enum):
    INICIADA = "INICIADA"
    EM_PAUSA = "EM_PAUSA"
    FINALIZADA = "FINALIZADA"
    CANCELADA = "CANCELADA"
    
class TipoMedidor(str, enum.Enum):
    TEMPERATURA = "TEMPERATURA"
    PRESSAO = "PRESSAO"
    VIBRACAO = "VIBRACAO"
    UMIDADE = "UMIDADE"
    CORRENTE = "CORRENTE"
    TENSAO = "TENSAO"
    POTENCIA = "POTENCIA"
    FLUXO = "FLUXO"
    NIVEL = "NIVEL"
    VELOCIDADE = "VELOCIDADE"
    RPM = "RPM"
    TEMPO = "TEMPO"

class UnidadeMedida(str, enum.Enum):
    CELSIUS = "°C"
    FAHRENHEIT = "°F"
    KELVIN = "K"

    BAR = "bar"
    PSI = "psi"
    PASCAL = "Pa"

    MMS = "mm/s"
    G = "g"

    PORCENTAGEM = "%"

    AMPERE = "A"

    VOLT = "V"

    WATT = "W"

    LITROS_POR_MINUTO = "L/min"

    METROS = "m"

    METROS_POR_SEGUNDO = "m/s"

    RPM = "rpm"
    HORAS = "horas"

# Tabela OrdemManutencao
class OrdemManutencao(Base):
    __tablename__ = "ordem_manutencao"
    
    id = Column(String, primary_key=True, index=True)
    numeroOS = Column(String, unique=True, index=True)
    planoManutencaoId = Column(String, nullable=True)
    equipamentoId = Column(String, nullable=True)
    tipoManutencao = Column(String, nullable=True)
    status = Column(String, nullable=True)
    prioridade = Column(String, nullable=True)
    dataAbertura = Column(DateTime, nullable=True)
    dataFechamento = Column(DateTime, nullable=True)
    solicitanteId = Column(String, nullable=True)
    responsavelId = Column(String, nullable=True)
    custoEstimado = Column(Float, nullable=True)
    custoReal = Column(Float, nullable=True)
    tempoParadaEstimado = Column(BigInteger, nullable=True)
    tempoParadaReal = Column(BigInteger, nullable=True)
    modoFalhaId = Column(String, nullable=True)
    causaRaizId = Column(String, nullable=True)
    
    def __repr__(self):
        return f"<OrdemManutencao(id={self.id}, numeroOS='{self.numeroOS}')>"

# Tabela ExecucaoOrdem
class ExecucaoOrdem(Base):
    __tablename__ = "execucao_ordem"
    
    id = Column(String, primary_key=True, index=True)
    ordemManutencaoId = Column(String, index=True)
    executorId = Column(String, nullable=True)
    dataInicio = Column(DateTime, nullable=True)
    dataFim = Column(DateTime, nullable=True)
    periodosDeTrabalho = Column(JSON, nullable=True)  # Armazena lista como JSON
    statusExecucao = Column(String, nullable=True)
    
    def __repr__(self):
        return f"<ExecucaoOrdem(id={self.id}, ordemManutencaoID='{self.ordemManutencaoID}')>"
    

# ------------ MODELOS ------------ #
class Equipamento(Base):
    __tablename__ = "equipamento"

    id = Column(String, primary_key=True)
    codigo = Column(String)
    nome = Column(String)
    localizacao = Column(String)
    numeroSerie = Column(String)
    statusOperacional = Column(String)
    criticidadeId = Column(String)

    # Relacionamento com medidores
    medidores = relationship("Medidor", back_populates="equipamento")


class Medidor(Base):
    __tablename__ = "medidor"

    id = Column(String, primary_key=True)
    equipamentoId = Column(String, ForeignKey("equipamento.id"))
    codigo = Column(String)
    nome = Column(String)
    tipo = Column(Enum(TipoMedidor, name="tipo_medidor"))  # ✅ aqui
    unidade = Column(Enum(UnidadeMedida, name="unidade_medida"))  # ✅ aqui
    valorAtual = Column(Float)
    valorMinimo = Column(Float)
    valorMaximo = Column(Float)

    equipamento = relationship("Equipamento", back_populates="medidores")