import requests
import os

EQUIPAMENTO_URL = os.getenv("EQUIPAMENTO_URL", "http://ativos:8090/api/equipamentos")

def get_equipamentos(jwt: str):
    """Busca todos os equipamentos cadastrados no serviço de ativos"""
    print(f"\n📊 Buscando equipamentos em: {EQUIPAMENTO_URL}")
    print(f"🔑 Usando JWT: {jwt[:30]}..." if jwt else "⚠️ JWT vazio!")
    
    try:
        headers = {"Authorization": f"Bearer {jwt}"}
        response = requests.get(EQUIPAMENTO_URL, headers=headers, timeout=10)
        
        print(f"📡 Status Code: {response.status_code}")
        
        response.raise_for_status()
        equipamentos = response.json()
        
        print(f"📦 Total de equipamentos recebidos: {len(equipamentos)}")

        if equipamentos:
            eq = equipamentos[0]
            print(f"\n📋 Exemplo do primeiro equipamento:")
            print(f"   ID: {eq.get('id')}")
            print(f"   Código: {eq.get('codigo')}")
            print(f"   Nome: {eq.get('nome')}")
            print(f"   Localização: {eq.get('localizacao')}")
            print(f"   Número de Série: {eq.get('numeroSerie')}")
            print(f"   Status Operacional: {eq.get('statusOperacional')}")
            print(f"   CriticidadeID: {eq.get('criticidadeID')}")
            print(f"   Medidores Vinculados: {eq.get('medidorIds')}")
        
        return equipamentos
    
    except requests.exceptions.HTTPError as e:
        print(f"❌ ERRO HTTP {e.response.status_code}: {e.response.text[:300]}")
        raise

    except Exception as e:
        print(f"❌ ERRO: {e}")
        raise