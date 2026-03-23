import requests
import os

EXECUCAO_URL = os.getenv("EXECUCAO_URL", "http://ordem-manutencao:8091/execucoes")

def get_execucoes(jwt: str):
    """Busca todas as execuções de ordens"""
    print(f"\n⚙️  Buscando execuções em: {EXECUCAO_URL}")
    print(f"🔑 Usando JWT: {jwt[:30]}..." if jwt else "⚠️  JWT vazio!")
    
    try:
        headers = {"Authorization": f"Bearer {jwt}"}
        response = requests.get(EXECUCAO_URL, headers=headers, timeout=10)
        
        print(f"📡 Status Code: {response.status_code}")
        
        response.raise_for_status()
        execucoes = response.json()
        
        print(f"📦 Total de execuções recebidas: {len(execucoes)}")
        
        if execucoes:
            print(f"\n📋 Exemplo da primeira execução:")
            print(f"   ID: {execucoes[0].get('id')}")
            print(f"   Ordem ID: {execucoes[0].get('ordemManutencaoID')}")
            print(f"   Status: {execucoes[0].get('statusExecucao')}")
        
        return execucoes
    
    except requests.exceptions.HTTPError as e:
        print(f"❌ ERRO HTTP {e.response.status_code}: {e.response.text[:300]}")
        raise
    except Exception as e:
        print(f"❌ ERRO: {e}")
        raise