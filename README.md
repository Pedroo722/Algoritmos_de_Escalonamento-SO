# Algoritmos de Escalonamento

Atividade para a disciplina de *Sistemas Operacionais*. Visando uma implementação em Java de algoritmos de escalonamento de processo.

Dupla:
- Pedro Henrique Alexandre
- Vinicius Cavalcante Pequeno


## Problema
Considere um sistema operacional que implementa o escalonamento de processos. O funcionamento esperado é que esse ambiente tenha N (número previamente informado pelo usuário) processos que podem chegar em tempos distintos para execução, e tempos de processamento diferentes (Parametrizaveis). O algoritmo pode gerar tempos randomicos para facilitar a simunação das execuções. Para cada processo, deve ser informado:
* Tempo de chegada
* Tempo de retorno
* Medias de tempos de chegada e retorno
* Tempo de ociosidade do processador, caso ocorra

Para o sistema como um todo deve se informar o tempo de troca de processos do sistema.

Algoritmos:
- FIFO
- SJF
- RR
- Prioridade
- LOT (Loteria)

## Saída
### FIFO - First in First Out
![FIFO](https://github.com/user-attachments/assets/e0ca8009-faea-4527-b1c9-d6ff0df922de)

### SJF - Shortest Job First
![SJF](https://github.com/user-attachments/assets/c8e8769e-d150-442a-8af9-deaa29a88b4d)

### RR - Round Robin
![RR](https://github.com/user-attachments/assets/da45c202-9d6c-4100-ac81-7374f3848ccb)

### Prioridade
![Prioridade](https://github.com/user-attachments/assets/e4bd12c2-4cda-4633-b566-5bc2b4e57654)

### LOT - Loteria
![LOT](https://github.com/user-attachments/assets/dd7df5d2-3124-4583-b06d-2c425e313fdd)
