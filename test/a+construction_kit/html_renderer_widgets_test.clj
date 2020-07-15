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
            [a+construction-kit.html-renderer-widgets :refer :all]
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


(deftest test-header-existence
  "Check that the a+construction-kit.html-renderer-widgets/header definition exists."
  (testing
    "if the a+construction-kit.html-renderer-widgets/header definition exists."
    (is (callable? 'a+construction-kit.html-renderer-widgets/header))))


(deftest test-navigation-bar-existence
  "Check that the a+construction-kit.html-renderer-widgets/navigation-bar definition exists."
  (testing
    "if the a+construction-kit.html-renderer-widgets/navigation-bar definition exists."
    (is (callable? 'a+construction-kit.html-renderer-widgets/navigation-bar))))


(deftest test-footer-existence
  "Check that the a+construction-kit.html-renderer-widgets/footer definition exists."
  (testing
    "if the a+construction-kit.html-renderer-widgets/footer definition exists."
    (is (callable? 'a+construction-kit.html-renderer-widgets/footer))))


(deftest test-back-button-existence
  "Check that the a+construction-kit.html-renderer-widgets/back-button definition exists."
  (testing
    "if the a+construction-kit.html-renderer-widgets/back-button definition exists."
    (is (callable? 'a+construction-kit.html-renderer-widgets/back-button))))


(deftest test-submit-button-existence
  "Check that the a+construction-kit.html-renderer-widgets/submit-button definition exists."
  (testing
    "if the a+construction-kit.html-renderer-widgets/submit-button definition exists."
    (is (callable? 'a+construction-kit.html-renderer-widgets/submit-button))))


(deftest test-add-button-existence
  "Check that the a+construction-kit.html-renderer-widgets/add-button definition exists."
  (testing
    "if the a+construction-kit.html-renderer-widgets/add-button definition exists."
    (is (callable? 'a+construction-kit.html-renderer-widgets/add-button))))


(deftest test-remove-button-existence
  "Check that the a+construction-kit.html-renderer-widgets/remove-button definition exists."
  (testing
    "if the a+construction-kit.html-renderer-widgets/remove-button definition exists."
    (is (callable? 'a+construction-kit.html-renderer-widgets/remove-button))))


(deftest test-disabled-submit-button-existence
  "Check that the a+construction-kit.html-renderer-widgets/disabled-submit-button definition exists."
  (testing
    "if the a+construction-kit.html-renderer-widgets/disabled-submit-button definition exists."
    (is (callable?
          'a+construction-kit.html-renderer-widgets/disabled-submit-button))))


(deftest test-radio-button-existence
  "Check that the a+construction-kit.html-renderer-widgets/radio-button definition exists."
  (testing
    "if the a+construction-kit.html-renderer-widgets/radio-button definition exists."
    (is (callable? 'a+construction-kit.html-renderer-widgets/radio-button))))


(deftest test-help-button-existence
  "Check that the a+construction-kit.html-renderer-widgets/help-button definition exists."
  (testing
    "if the a+construction-kit.html-renderer-widgets/help-button definition exists."
    (is (callable? 'a+construction-kit.html-renderer-widgets/help-button))))


(deftest test-canvas-existence
  "Check that the a+construction-kit.html-renderer-widgets/canvas definition exists."
  (testing
    "if the a+construction-kit.html-renderer-widgets/canvas definition exists."
    (is (callable? 'a+construction-kit.html-renderer-widgets/canvas))))


(deftest test-drop-down-existence
  "Check that the a+construction-kit.html-renderer-widgets/drop-down definition exists."
  (testing
    "if the a+construction-kit.html-renderer-widgets/drop-down definition exists."
    (is (callable? 'a+construction-kit.html-renderer-widgets/drop-down))))

