FROM python:3.11-slim

WORKDIR /app

# Copiar requirements
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# Copiar código
COPY app/ ./app/

# Adicionar ao PYTHONPATH
ENV PYTHONPATH=/app

# Comando para executar
CMD ["python", "-m", "app.main"]