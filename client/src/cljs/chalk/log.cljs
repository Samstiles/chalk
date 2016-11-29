(ns chalk.logs)

(defn i
  ([msg] (i msg nil))
  ([msg data] (.info js/console msg data)))

(defn e
  ([msg] (e msg nil))
  ([msg data] (.error js/console msg data)))

(defn l
  ([msg] (l msg nil))
  ([msg data] (.log js/console msg data)))

(defn w
  ([msg] (w msg nil))
  ([msg data] (.warn js/console msg data)))
