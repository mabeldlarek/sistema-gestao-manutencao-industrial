import requests
import os

ORDEM_URL = os.getenv("ORDEM_URL", "http://ordem-manutencao:8091/api/os")

def get_ordens(jwt: str):
    """Busca todas as ordens de manutenção"""
    print(f"\n📊 Buscando ordens em: {ORDEM_URL}")
    print(f"🔑 Usando JWT: {jwt[:30]}..." if jwt else "⚠️  JWT vazio!")
    
    try:
        headers = {"Authorization": f"Bearer {jwt}"}
        response = requests.get(ORDEM_URL, headers=headers, timeout=10)
        
        print(f"📡 Status Code: {response.status_code}")
        
        response.raise_for_status()
        ordens = response.json()
        
        print(f"📦 Total de ordens recebidas: {len(ordens)}")
        
        if ordens:
            print(f"\n📋 Exemplo da primeira ordem:")
            print(f"   ID: {ordens[0].get('id')}")
            print(f"   Número OS: {ordens[0].get('numeroOS')}")
            print(f"   Status: {ordens[0].get('status')}")
        
        return ordens
    
    except requests.exceptions.HTTPError as e:
        print(f"❌ ERRO HTTP {e.response.status_code}: {e.response.text[:300]}")
        raise
    except Exception as e:
        print(f"❌ ERRO: {e}")
        raise

def get_finalizadas(jwt: str):
    """Busca apenas ordens finalizadas"""
    ordens = get_ordens(jwt)
    finalizadas = [o for o in ordens if o.get("status") == "FINALIZADA"]
    print(f"✅ Ordens finalizadas: {len(finalizadas)}")
    return finalizadas