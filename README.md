# scrambled clojure rest application
Created by Leonardo Afonso as small test.

## Prerequisites

You will need [Leiningen][1] 2.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein run

to test, send a GET request with 2 query parameters, scrambledString for the string ann word to check inside the string. Example

    curl --request GET https://localhost:3000/?scrambledString=cedewaraaossoqqyt&word=codewars

the request will return a boolean value indicating if the word can be formed with all the letter in the string.

## License

Copyright © 2021
