# Use Python 3.12 slim
FROM python:3.12-slim

# Defina diretório de trabalho
WORKDIR /app

# Copie dependências
COPY requirements.txt .

# Instale dependências
RUN pip install --no-cache-dir -r requirements.txt

# Copie código da aplicação
COPY app/ ./app

# Defina variável de ambiente para evitar buffer de saída
ENV PYTHONUNBUFFERED=1

# Comando padrão para rodar a aplicação
CMD ["python", "app/main.py"]
