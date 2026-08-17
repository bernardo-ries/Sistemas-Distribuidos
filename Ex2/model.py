class FilialModel:
    def __init__(self, vendas):
        self.vendas = vendas
        self.total = 0

    def calcular_total(self):
        self.total = sum(self.vendas)

    def get_total(self):
        return self.total
