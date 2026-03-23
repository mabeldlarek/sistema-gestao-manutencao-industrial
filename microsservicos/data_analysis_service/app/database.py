from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker
import os

# URL do banco de dados
DATABASE_URL = os.getenv("DATABASE_URL", "postgresql://postgres:postgres@ct-postgres:5432/analise_db")

print(f"🔗 Conectando ao banco: {DATABASE_URL}")

# Criar engine
engine = create_engine(DATABASE_URL)

# Session local
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)

# Base para os modelos
Base = declarative_base()

def init_db():
    """Inicializa o banco de dados criando todas as tabelas"""
    print("📊 Criando tabelas no banco de dados...")
    Base.metadata.create_all(bind=engine)
    print("✅ Tabelas criadas com sucesso!")

def get_db():
    """Retorna uma sessão do banco"""
    db = SessionLocal()
    try:
        return db
    finally:
        db.close()