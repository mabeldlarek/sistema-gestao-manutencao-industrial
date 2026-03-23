import requests
import os

MEDIDOR_URL = os.getenv("MEDIDOR_URL", "http://ativos:8090/api/medidores")

def get_medidores(jwt: str):
    """Busca todos os medidores cadastrados no serviço de ativos"""
    print(f"\n📊 Buscando medidores em: {MEDIDOR_URL}")
    print(f"🔑 Usando JWT: {jwt[:30]}..." if jwt else "⚠️ JWT vazio!")
    
    try:
        headers = {"Authorization": f"Bearer {jwt}"}
        response = requests.get(MEDIDOR_URL, headers=headers, timeout=10)
        
        print(f"📡 Status Code: {response.status_code}")
        
        response.raise_for_status()
        medidores = response.json()
        
        print(f"📦 Total de medidores recebidos: {len(medidores)}")

        if medidores:
            m = medidores[0]
            print(f"\n📋 Exemplo do primeiro medidor:")
            print(f"   ID: {m.get('id')}")
            print(f"   Equipamento ID: {m.get('equipamentoId')}")
            print(f"   Código: {m.get('codigo')}")
            print(f"   Nome: {m.get('nome')}")
            print(f"   Tipo: {m.get('tipo')}")
            print(f"   Unidade: {m.get('unidade')}")
            print(f"   Valor Atual: {m.get('valorAtual')}")
            print(f"   Valor Mínimo: {m.get('valorMinimo')}")
            print(f"   Valor Máximo: {m.get('valorMaximo')}")

        return medidores
    
    except requests.exceptions.HTTPError as e:
        print(f"❌ ERRO HTTP {e.response.status_code}: {e.response.text[:300]}")
        raise

    except Exception as e:
        print(f"❌ ERRO: {e}")
        raise
