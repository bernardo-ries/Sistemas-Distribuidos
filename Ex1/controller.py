def vender(caixa, model):
    vendas = 1000
    preco = 10

    for i in range(vendas):
        model.adicionar_valor(preco)

    print(f"Caixa {caixa} terminou suas {vendas} vendas.")
