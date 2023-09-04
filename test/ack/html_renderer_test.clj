;
;  (C) Copyright 2017, 2020, 2023  Pavel Tisnovsky
;
;  All rights reserved. This program and the accompanying materials
;  are made available under the terms of the Eclipse Public License v1.0
;  which accompanies this distribution, and is available at
;  http://www.eclipse.org/legal/epl-v10.html
;
;  Contributors:
;      Pavel Tisnovsky
;

(ns ack.config-test
  (:require [clojure.test :refer :all]
            [ack.html-renderer :refer :all]
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


(deftest test-render-select-app-page-existence
  "Check that the ack.html-renderer/render-select-app-page definition exists."
  (testing
    "if the ack.html-renderer/render-select-app-page definition exists."
    (is (callable? 'ack.html-renderer/render-select-app-page))))


(deftest test-render-app-type-existence
  "Check that the ack.html-renderer/render-app-type definition exists."
  (testing
    "if the ack.html-renderer/render-app-type definition exists."
    (is (callable? 'ack.html-renderer/render-app-type))))


(deftest test-language-icon-existence
  "Check that the ack.html-renderer/language-icon definition exists."
  (testing
    "if the ack.html-renderer/language-icon definition exists."
    (is (callable? 'ack.html-renderer/language-icon))))


(deftest test-render-select-language-page-existence
  "Check that the ack.html-renderer/render-select-language-page definition exists."
  (testing
    "if the ack.html-renderer/render-select-language-page definition exists."
    (is (callable?
          'ack.html-renderer/render-select-language-page))))


(deftest test-render-languages-existence
  "Check that the ack.html-renderer/render-languages definition exists."
  (testing
    "if the ack.html-renderer/render-languages definition exists."
    (is (callable? 'ack.html-renderer/render-languages))))


(deftest test-configuration->id-existence
  "Check that the ack.html-renderer/configuration->id definition exists."
  (testing
    "if the ack.html-renderer/configuration->id definition exists."
    (is (callable? 'ack.html-renderer/configuration->id))))


(deftest test-render-microservice-config-existence
  "Check that the ack.html-renderer/render-microservice-config definition exists."
  (testing
    "if the ack.html-renderer/render-microservice-config definition exists."
    (is (callable?
          'ack.html-renderer/render-microservice-config))))


(deftest test-render-application-config-existence
  "Check that the ack.html-renderer/render-application-config definition exists."
  (testing
    "if the ack.html-renderer/render-application-config definition exists."
    (is (callable?
          'ack.html-renderer/render-application-config))))


(deftest test-render-cli-tool-config-existence
  "Check that the ack.html-renderer/render-cli-tool-config definition exists."
  (testing
    "if the ack.html-renderer/render-cli-tool-config definition exists."
    (is (callable? 'ack.html-renderer/render-cli-tool-config))))


(deftest test-render-openshift-cron-job-config-existence
  "Check that the ack.html-renderer/render-openshift-cron-job-config definition exists."
  (testing
    "if the ack.html-renderer/render-openshift-cron-job-config definition exists."
    (is (callable?
          'ack.html-renderer/render-openshift-cron-job-config))))


(deftest test-render-desktop-config-existence
  "Check that the ack.html-renderer/render-desktop-config definition exists."
  (testing
    "if the ack.html-renderer/render-desktop-config definition exists."
    (is (callable? 'ack.html-renderer/render-desktop-config))))


(deftest test-render-configure-modules-form-existence
  "Check that the ack.html-renderer/render-configure-modules-form definition exists."
  (testing
    "if the ack.html-renderer/render-configure-modules-form definition exists."
    (is (callable?
          'ack.html-renderer/render-configure-modules-form))))


(deftest test-render-configure-modules-page-existence
  "Check that the ack.html-renderer/render-configure-modules-page definition exists."
  (testing
    "if the ack.html-renderer/render-configure-modules-page definition exists."
    (is (callable?
          'ack.html-renderer/render-configure-modules-page))))

