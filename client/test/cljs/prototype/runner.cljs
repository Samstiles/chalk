(ns prototype.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [prototype.core-test]))

(doo-tests 'prototype.core-test)
