function enableNextButton() {
    var button = document.getElementById("next");
    button.disabled = false;
}

var language_parts = {};

function languagePartSelected(part, app_parts) {
    language_parts[part] = true;
    var cnt = Object.keys(language_parts).length;
    if (cnt >= app_parts) {
        enableNextButton();
    }
}

function onSelectPrimaryLanguageIcon(language) {
    var id = "primary-language" + "-" + language;
    var element = document.getElementById(id);
    if (element != null) {
        element.checked = true;
        enableNextButton();
    }
}

function onSelectLanguagePartIcon(part, language, app_parts) {
    var id = part + "-language" + "-" + language;
    var element = document.getElementById(id);
    if (element != null) {
        element.checked = true;
    }
    languagePartSelected(part, app_parts);
}

var paper = null;

function createPaper(width, height) {
    paper = new Raphael(document.getElementById('canvas_container'), width, height);
}

function drawAppSchema(paper) {
    var circle = paper.circle(100, 100, 80);
}

