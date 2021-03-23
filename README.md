# todos.service

In this project, I'm trying to implement simple Todo list managing app in Clojure. 
Main idea is to learn/experiment/try different Clojure libs, frameworks and tools.   

## Project Goals

- [x] Build simple web service on top of [Ring](https://github.com/ring-clojure/ring)
- [x] Implement request routing using Compojure [Compojure](https://github.com/weavejester/compojure)
- [ ] Access database using [next.jdbc](https://github.com/seancorfield/next-jdbc) and [HoneySql](https://github.com/seancorfield/honeysql)
- [ ] Use [C3p0](https://github.com/swaldman/c3p0) db connection pool
- [ ] Use [Component](https://github.com/stuartsierra/component) for app state and components lifecycle management
- [ ] Use [buddy](https://github.com/funcool/buddy) to secure app
- [ ] Document API
- [ ] Dockerize

## Usage

Run the project directly, via `:main-opts` (`-m vlko.todos.service`):

    $ clojure -M:run

Run the project's tests (they'll fail until you edit them):

    $ clojure -M:test:runner

Build an uberjar:

    $ clojure -X:uberjar

This will update the generated `pom.xml` file to keep the dependencies synchronized with
your `deps.edn` file. You can update the version (and SCM tag) information in the `pom.xml` using the
`:version` argument:

    $ clojure -X:uberjar :version '"1.2.3"'

If you don't want the `pom.xml` file in your project, you can remove it, but you will
also need to remove `:sync-pom true` from the `deps.edn` file (in the `:exec-args` for `depstar`).

Run that uberjar:

    $ java -jar todos.service.jar
