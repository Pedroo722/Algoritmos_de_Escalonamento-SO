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
![FIFO](https://github.com/user-attachments/assets/49986feb-a694-4e64-a945-ab6c665d171c)

### SJF - Shortest Job First
![SJF](https://github.com/user-attachments/assets/92903099-c641-4f54-9162-e806d72cde4b)

### RR - Round Robin

### Prioridade
![Prioridade](https://github.com/user-attachments/assets/161fbc58-51ef-4f35-9410-6d917ec33d75)

### LOT - Loteria
![Loteria](https://github.com/user-attachments/assets/254298cc-7286-45f3-9fee-fec72ba14d97)
