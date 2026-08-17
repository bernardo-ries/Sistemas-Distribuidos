import threading


class CaixaModel:
    def __init__(self):
        self.saldo_central = 0
        self.lock = threading.Lock()

    def adicionar_valor(self, valor):
        with self.lock:
            self.saldo_central += valor

    def get_saldo(self):
        return self.saldo_central
