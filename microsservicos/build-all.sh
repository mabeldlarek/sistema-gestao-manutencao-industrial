#!/bin/bash
set -e

echo "🔹 Gerando todos os JARs..."

MICROS=("ativos" "conteudo" "identidade-acesso" "material-estoque" "ordem-manutencao")

for app in "${MICROS[@]}"; do
  echo "📦 Build do $app..."
  (cd $app && mvn clean package -DskipTests)
done

echo "✅ Todos os JARs foram gerados!"