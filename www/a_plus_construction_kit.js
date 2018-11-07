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

function readDropDownValue(drop_down_id) {
    var drop_down = document.getElementById(drop_down_id);
    return drop_down.options[drop_down.selectedIndex].value;
}

// drawing-related part

var MAX_DATABASES = 7;

var paper = null;
var databases = [];

function createPaper(width, height) {
    paper = new Raphael(document.getElementById('canvas_container'), width, height);
    paper.drawn = {};
}

function drawInternetIcon(paper) {
    var image = "icons/internet.jpg";
    paper.image(image, paper.width/2 - 145/2, 10, 145, 85);
    paper.text(paper.width/2, 60, "Internet").attr("font-size", 16).attr("fill", "white");
}

function drawAppSchemaWithOneLanguage(paper, language) {
    var circle = paper.circle(100, 100, 80);
    var image = "languages/128x128/" + language + ".png";
    paper.image(image, paper.width/2 - 64, paper.height/2 - 64, 128, 128);
    paper.rect(paper.width/2 - 64, paper.height/2 - 64, 128, 128).attr("stroke", "#088");
    if (app_type == "microservice") {
        drawInternetIcon(paper);
    }
}

function drawAppSchemaWithTwoLanguages(paper, app_type, languages) {
    var circle = paper.circle(100, 100, 80);
}

function drawAppSchema(paper, app_type, languages) {
    if ("primary-language" in languages) {
        var primary_language = languages["primary-language"];
        drawAppSchemaWithOneLanguage(paper, app_type, primary_language);
    }
    else {
        drawAppSchemaWithTwoLanguages(paper, app_type, languages);
    }
}

function deleteDrawnObject(paper, selector) {
    var to_delete = paper.drawn[selector];
    var i;
    for (i=0; i < to_delete.length; i++) {
        to_delete[i].remove();
    }
    paper.drawn[selector] = null;
}

// event handlers

function onFrameworkAdd(selected) {
    if (selected == null) {
        return;
    }

    var drawn = paper.drawn["web_service_framework"];
    if (drawn != null) {
        deleteDrawnObject(paper, "web_service_framework");
    }
    drawn = paper.drawn["web_service_framework"] = [];
    drawn.push(paper.path("M320,256L320,92").attr("stroke-dasharray", "--").attr("stroke", "gray").attr("stroke-width", "2").attr("arrow-end", "block-wide-long"));
    drawn.push(paper.rect(paper.width/2 - 64, 140, 128, 64).attr("stroke", "#088").attr("fill", "white"));
    drawn.push(paper.text(paper.width/2, 172, selected).attr("font-size", 16));
}

function onFrameworkRemove() {
    deleteDrawnObject(paper, "web_service_framework");
}

function onAddApplicationPart(language, configuration, drop_down_id) {
    console.log("command: ADD")
    console.log("language: " + language);
    console.log("config:   " + configuration);
    console.log("key:      " + drop_down_id);
    var value = readDropDownValue(drop_down_id);
    console.log("value:    " + value);

    switch (configuration) {
    case "Web service framework":
        onFrameworkAdd(value);
        break;
    }
}

function onRemoveApplicationPart(language, configuration, drop_down_id) {
    var value = readDropDownValue(drop_down_id);

    switch (configuration) {
    case "Web service framework":
        onFrameworkRemove();
        break;
    }
}
