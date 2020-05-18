**Характеристики ПК:**
- процессор: Intel Core i5 7200U (2500 МГц)
- объем оперативной памяти: 8 ГБ

**JVM:** OpenJDK 64-Bit Server VM (13+33, mixed mode, sharing)
**Java:** version 13, vendor Oracle Corporation

**Измеряемые сборщики:** Serial Collector, G1

**Результаты измерений работы:**

Сборщик мусора|Стартовый объем памяти|Максимальный объем памяти|Кол-во сборок|Время на сборки (sec)|
:---:|:---:|:---:|:---:|:---:|
Serial Collector (Copy)|256m|256m|4|0.469
Serial Collector (MarkSweepCompact)|256m|256m|9|3.15
Serial Collector (Copy)|2048m|2048m|4|3.552
Serial Collector (MarkSweepCompact)|2048m|2048m|3|8.216
Parallel Collector(Scavenge)|256m|256m|3|0.169
Parallel Collector(MarkSweep)|256m|256m|без OOM|~4,724 на сборку
Parallel Collector(Scavenge)|2048m|2048m|3|3.411
Parallel Collector(MarkSweep)|2048m|2048m|7|26.489
G1 (G1 Young Generation) |256m|256m|22|0.578
G1 (G1 Old Generation) |256m|256m|10|2.549
G1 (G1 Yong Generation) |2048m|2048m|20|5.661
G1 (G1 Old Generation) |2048m|2048m|5|17.684

**Заключение:** 
При проведении экспериментов использования различных сборщиков мусора эффективнее всех показал 
себя сборщик Parallel Collector. Несмотря на достаточно большое потребление ресурсов и
большие паузы на сборки(растущие с увеличением стартовых параметров памяти) количество сборок больше.
Также при использовании сборщика мусора Parallel Collector при стартовом и максимальном объеме памяти 256 МБ
 приложение не падает с OOM длительное время, тогда как при использовании сборщиков G1 и Serial приложение падает с OOM в течении 5 минут.


