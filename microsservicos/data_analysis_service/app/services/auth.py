import requests
import os

IDENTIDADE_URL = os.getenv("IDENTIDADE_URL", "http://identidade-acesso:8080/login")

def login(username: str, password: str) -> str:
    print(f"🔐 Tentando login em: {IDENTIDADE_URL}")
    print(f"👤 Usuário: {username}")
    
    try:
        payload = {"username": username, "password": password}
        response = requests.post(IDENTIDADE_URL, json=payload, timeout=10)
        
        print(f"📡 Status Code: {response.status_code}")
        print(f"📄 Response: {response.text[:200]}")
        
        response.raise_for_status()
        data = response.json()
        
        token = data.get("accessToken")
        if token:
            print(f"✅ Token obtido com sucesso!")
            print(f"🔑 Token (preview): {token[:50]}...")
        else:
            print(f"⚠️  Chaves disponíveis: {list(data.keys())}")
        
        return token
    
    except requests.exceptions.ConnectionError as e:
        print(f"❌ ERRO DE CONEXÃO: {e}")
        raise
    except requests.exceptions.HTTPError as e:
        print(f"❌ ERRO HTTP {e.response.status_code}: {e.response.text}")
        raise
    except Exception as e:
        print(f"❌ ERRO: {e}")
        raise