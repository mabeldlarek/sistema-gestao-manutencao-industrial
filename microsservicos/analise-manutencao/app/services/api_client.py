import requests

IDENTIDADE_URL = "http://identidade-acesso:8080/login"
ORDEM_MANUTENCAO_URL = "http://ordem-manutencao:8091"

def get_jwt(username: str, password: str) -> str:
    """
    Faz login e retorna o JWT.
    """
    payload = {"username": username, "password": password}
    response = requests.post(IDENTIDADE_URL, json=payload)
    response.raise_for_status()
    data = response.json()
    return data.get("access_token")  # Ajuste conforme o formato do seu JWT

def get_ordens(jwt: str):
    """
    Faz requisição para o microserviço de ordens de manutenção usando o JWT.
    """
    headers = {"Authorization": f"Bearer {jwt}"}
    response = requests.get(f"{ORDEM_MANUTENCAO_URL}/api/os", headers=headers)
    response.raise_for_status()
    return response.json()
