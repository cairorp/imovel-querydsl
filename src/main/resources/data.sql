CREATE TABLE IF NOT EXISTS IMOVEL
(
    IDT_IMOVEL INT AUTO_INCREMENT PRIMARY KEY,
    ST_LOCACAO BIT NOT NULL,
    QTD_QUARTO INT NOT NULL,
    IDT_PESSOA INT NOT NULL
);