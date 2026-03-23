from services.api_client import get_jwt, get_ordens

def main():
    # Usuário e senha de teste
    username = "ADMIN"
    password = "123"

    print("Obtendo JWT...")
    jwt = get_jwt(username, password)
    print(f"JWT recebido: {jwt}")

    print("Requisitando dados do microserviço de ordens...")
    ordens = get_ordens(jwt)
    print(f"Dados recebidos: {ordens}")

if __name__ == "__main__":
    main()
