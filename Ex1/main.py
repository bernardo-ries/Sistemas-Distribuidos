import threading

from model import CaixaModel
from controller import vender


if __name__ == "__main__":
    model = CaixaModel()

    threads = []

    for i in range(1, 6):
        thread = threading.Thread(target=vender, args=(i, model))

        threads.append(thread)
        thread.start()

    for thread in threads:
        thread.join()

    print(f"Saldo final: R$ {model.get_saldo()}")
