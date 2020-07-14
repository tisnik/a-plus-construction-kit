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
            [a+construction-kit.server :refer :all]
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


(deftest test-finish-processing-existence
  "Check that the a+construction-kit.server/finish-processing definition exists."
  (testing "if the a+construction-kit.server/finish-processing definition exists."
    (is (callable? 'a+construction-kit.server/finish-processing))))


(deftest test-process-select-app-page-existence
  "Check that the a+construction-kit.server/process-select-app-page definition exists."
  (testing "if the a+construction-kit.server/process-select-app-page definition exists."
    (is (callable? 'a+construction-kit.server/process-select-app-page))))


(deftest test-app-type-name->label-existence
  "Check that the a+construction-kit.server/app-type-name->label definition exists."
  (testing "if the a+construction-kit.server/app-type-name->label definition exists."
    (is (callable? 'a+construction-kit.server/app-type-name->label))))


(deftest test-read-languages-existence
  "Check that the a+construction-kit.server/read-languages definition exists."
  (testing "if the a+construction-kit.server/read-languages definition exists."
    (is (callable? 'a+construction-kit.server/read-languages))))


(deftest test-process-select-language-page-existence
  "Check that the a+construction-kit.server/process-select-language-page definition exists."
  (testing "if the a+construction-kit.server/process-select-language-page definition exists."
    (is (callable? 'a+construction-kit.server/process-select-language-page))))


(deftest test-process-configure-modules-page-existence
  "Check that the a+construction-kit.server/process-configure-modules-page definition exists."
  (testing "if the a+construction-kit.server/process-configure-modules-page definition exists."
    (is (callable? 'a+construction-kit.server/process-configure-modules-page))))


(deftest test-uri->file-name-existence
  "Check that the a+construction-kit.server/uri->file-name definition exists."
  (testing "if the a+construction-kit.server/uri->file-name definition exists."
    (is (callable? 'a+construction-kit.server/uri->file-name))))


(deftest test-gui-call-handler-existence
  "Check that the a+construction-kit.server/gui-call-handler definition exists."
  (testing "if the a+construction-kit.server/gui-call-handler definition exists."
    (is (callable? 'a+construction-kit.server/gui-call-handler))))


(deftest test-handler-existence
  "Check that the a+construction-kit.server/handler definition exists."
  (testing "if the a+construction-kit.server/handler definition exists."
    (is (callable? 'a+construction-kit.server/handler))))

