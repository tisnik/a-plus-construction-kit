;
;  (C) Copyright 2018, 2020, 2023  Pavel Tisnovsky
;
;  All rights reserved. This program and the accompanying materials
;  are made available under the terms of the Eclipse Public License v1.0
;  which accompanies this distribution, and is available at
;  http://www.eclipse.org/legal/epl-v10.html
;
;  Contributors:
;      Pavel Tisnovsky
;

(ns a+construction-kit.core-test
  (:require [clojure.test :refer :all]
            [a+construction-kit.core :refer :all]))

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

(deftest test-start-server-existence
  "Check that the a+construction-kit.core/start-server definition exists."
  (testing "if the a+construction-kit.core/start-server definition exists."
           (is (callable? 'a+construction-kit.core/start-server))))


(deftest test-show-help-existence
  "Check that the a+construction-kit.core/show-help definition exists."
  (testing "if the a+construction-kit.core/show-help definition exists."
           (is (callable? 'a+construction-kit.core/show-help))))


(deftest test-show-configuration-existence
  "Check that the a+construction-kit.core/show-configuration definition exists."
  (testing
    "if the a+construction-kit.core/show-configuration definition exists."
    (is (callable? 'a+construction-kit.core/show-configuration))))


(deftest test--main-existence
  "Check that the a+construction-kit.core/-main definition exists."
  (testing "if the a+construction-kit.core/-main definition exists."
           (is (callable? 'a+construction-kit.core/-main))))

