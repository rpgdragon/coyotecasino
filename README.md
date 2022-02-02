# Coyote Casino
Programa que permite comparar si el Casino de Looney Tunes Mayhem (https://looneytuneswom.scopely.com/) es efectivamente 25%. 


El juego de la cantina en los Looney Tunes es un evento diario de 40 rondas en el cual se pueden conseguir premios, arriesgando a levantar una de las 4 cartas que nos ofrece el Coyote por ronda. 3 de las cartas contendrán premios y la última de las cartas hará que perdamos todos los premiso que tenemos hasta ahora. Cada 5 rondas, se entra en una ronda de bonus. Las rondas bonus además de dar un premio adicional, en dicha ronda, las 4 rondas contendrán premios. Además, la primera ronda, tiene premios seguro por lo que sólo se puede perder a partir de la ronda 2.


Este proyecto surge de la necesidad de conocer si es más fácil llegar a 40 rondas en un proyecto con un 25% de probabilidades de perder en cada ronda que en el propio juego de Looney Tunes Mayhem, donde es muy raro superar la ron


Este programa recibe los siguientes parametros de forma obligatoria:


- **NumPartidas**: Número de partidas enteras que se van a jugar. Una partida entera se entiende, es desde que se levanta la primera carta en la primera ronda, hasta que se llegue a la ronda 40 o se levante la carta coyote.
- **debeContinuar**: True: Indica que aunque saque el coyote, continue con la partida. Esto hará que se muestre el número de gemas gastadas en cada partida. False: La partida se termina al sacar una carta coyote, el número de gemas siempre será 0.

El resultado del programa será el siguiente:


*Partida  RondaFinal  NumCoyotes  GemasGastadas*

   *1          4           1            0*
   
   *2          7           1            0*
   
   *......................................*
   
   *......................................*
   
   *TotalPartidas: 25*
   
   *TotalRondas: 156*
   
   *NumCoyotes: 25*
   
   *RatioRondasCoyote: 6.24 rondas*
   
   *%Coyote: 21.45%*
   
   *TotalGemas: 0*
   
