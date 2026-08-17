import threading

from model import FilialModel
from controller import processar_filial


if __name__ == "__main__":

    vendas_filial1 = [10] * 10000
    vendas_filial2 = [20] * 10000
    vendas_filial3 = [30] * 10000
    vendas_filial4 = [40] * 10000

    filiais = [
        FilialModel(vendas_filial1),
        FilialModel(vendas_filial2),
        FilialModel(vendas_filial3),
        FilialModel(vendas_filial4)
    ]

    threads = []

    for filial in filiais:
        thread = threading.Thread(
            target=processar_filial,
            args=(filial,)
        )

        threads.append(thread)
        thread.start()

    for thread in threads:
        thread.join()

    total_geral = 0

    for filial in filiais:
        total_geral += filial.get_total()

    print(f"Faturamento total: R$ {total_geral}")
