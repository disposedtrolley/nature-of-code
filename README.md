# (nature-of-code)

Code snippets and exercises from reading [The Nature of Code](https://natureofcode.com/book) implemented in Clojure, using [quil](https://github.com/quil/quil).

## Development

### Prerequisites

- JDK 8, i.e. `brew install openjdk@8`
- [Clojure CLI tools](https://clojure.org/guides/getting_started#_clojure_installer_and_cli_tools)

## Running

`src` is organised into several namespaces, each declaring a `run` function as its entrypoint. `clj -X <namespace>/run` can be used to execute the entrypoint of a namespace.

For example, to run the vectors chapter, execute `clj -X chapters.vectors/run`.