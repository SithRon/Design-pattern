L'interface javax.sql.DataSource existe déjà depuis le jdk 1.6
Il n'est donc pas nécessaire de ré-inventer la roue

Cependant cette interface comporte des méthodes un peu inutiles dans les cas simples

Seule la méthode "getConnection()" est importante 