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

(ns ack.core-test
  (:require [clojure.test :refer :all]
            [ack.core :refer :all]))

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
  "Check that the ack.core/start-server definition exists."
  (testing "if the ack.core/start-server definition exists."
           (is (callable? 'ack.core/start-server))))


(deftest test-show-help-existence
  "Check that the ack.core/show-help definition exists."
  (testing "if the ack.core/show-help definition exists."
           (is (callable? 'ack.core/show-help))))


(deftest test-show-configuration-existence
  "Check that the ack.core/show-configuration definition exists."
  (testing
    "if the ack.core/show-configuration definition exists."
    (is (callable? 'ack.core/show-configuration))))


(deftest test--main-existence
  "Check that the ack.core/-main definition exists."
  (testing "if the ack.core/-main definition exists."
           (is (callable? 'ack.core/-main))))

