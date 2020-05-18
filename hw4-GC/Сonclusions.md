**Характеристики ПК:**
- процессор: Intel Core i5 7200U (2500 МГц)
- объем оперативной памяти: 8 ГБ

**JVM:** OpenJDK 64-Bit Server VM (13+33, mixed mode, sharing)
**Java:** version 13, vendor Oracle Corporation

**Измеряемые сборщики:** Serial Collector, G1, Parallel Collector

**Результаты измерений работы:**

Сборщик мусора|Стартовый объем памяти|Максимальный объем памяти|Кол-во сборок|Время на сборки (sec)|
:---:|:---:|:---:|:---:|:---:|
Serial Collector (Copy)|256m|256m|4|0.383
Serial Collector (MarkSweepCompact)|256m|256m|30|9.206
Serial Collector (Copy)|2048m|2048m|4|2.323
Serial Collector (MarkSweepCompact)|2048m|2048m|3|7.459
Parallel Collector(Scavenge)|256m|256m|3|0.325
Parallel Collector(MarkSweep)|256m|256m|43|19.564
Parallel Collector(Scavenge)|2048m|2048m|3|2.053
Parallel Collector(MarkSweep)|2048m|2048m|9|35.248
G1 (G1 Young Generation) |256m|256m|19|0.427
G1 (G1 Old Generation) |256m|256m|4|0.979
G1 (G1 Yong Generation) |2048m|2048m|23|3.103
G1 (G1 Old Generation) |2048m|2048m|2|4.78

**Заключение:** 
При проведении экспериментов использования различных сборщиков мусора эффективнее всех показал 
себя сборщик G1. Несмотря на достаточно большое потребление ресурсов отношение количества сборок при небольшом их времени более оптимально по сравнению со сборщиками Serial и Parallel.
Также при использовании сборщика мусора G1 при стартовом и максимальном объеме памяти 2048 МБ
 приложение не падало с OOM длительное время (~20 минут), тогда как при использовании сборщиков Parallel и Serial приложение падает с OOM в течении 5 минут.


