CREATE TABLE IF NOT EXISTS DadosCovid (
dataOcorrencia String,
siglaPais String,
descPais String,
regiao String,
novosCasos int,
casosAcumulados int,
novosObitos int,
obitosAcumulados int
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
STORED AS TEXTFILE LOCATION '/Desafio/dados_covid';
