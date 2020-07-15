;
;  (C) Copyright 2017, 2020  Pavel Tisnovsky
;
;  All rights reserved. This program and the accompanying materials
;  are made available under the terms of the Eclipse Public License v1.0
;  which accompanies this distribution, and is available at
;  http://www.eclipse.org/legal/epl-v10.html
;
;  Contributors:
;      Pavel Tisnovsky
;

(ns a+construction-kit.config-test
  (:require [clojure.test :refer :all]
            [a+construction-kit.config :refer :all]
            [clojure.pprint :as pprint]))

;
; Common functions used by tests.
;

(defn callable?
  "Test if given function-name is bound to the real function."
  [function-name]
  (clojure.test/function? function-name))

;
; Tests for various functions
;

(deftest test-full-prefix-existence
  "Check that the a+construction-kit.config/full-prefix definition exists."
  (testing "if the a+construction-kit.config/full-prefix definition exists."
           (is (callable? 'a+construction-kit.config/full-prefix))))


(deftest test-update-configuration-existence
  "Check that the a+construction-kit.config/update-configuration definition exists."
  (testing
    "if the a+construction-kit.config/update-configuration definition exists."
    (is (callable? 'a+construction-kit.config/update-configuration))))


(deftest test-load-configuration-from-ini-existence
  "Check that the a+construction-kit.config/load-configuration-from-ini definition exists."
  (testing
    "if the a+construction-kit.config/load-configuration-from-ini definition exists."
    (is (callable? 'a+construction-kit.config/load-configuration-from-ini))))


(deftest test-pretty-print?-existence
  "Check that the a+construction-kit.config/pretty-print? definition exists."
  (testing "if the a+construction-kit.config/pretty-print? definition exists."
           (is (callable? 'a+construction-kit.config/pretty-print?))))


(deftest test-verbose?-existence
  "Check that the a+construction-kit.config/verbose? definition exists."
  (testing "if the a+construction-kit.config/verbose? definition exists."
           (is (callable? 'a+construction-kit.config/verbose?))))

