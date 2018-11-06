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
    paper.drawn = {};
    paper.drawn["web_service_framework"] = null;
}

function drawAppSchemaWithOneLanguage(paper, language) {
    var circle = paper.circle(100, 100, 80);
    var image = "languages/128x128/" + language + ".png";
    paper.image(image, paper.width/2 - 64, paper.height/2 - 64, 128, 128);
    paper.rect(paper.width/2 - 64, paper.height/2 - 64, 128, 128).attr("stroke", "#088");
}

function drawAppSchemaWithTwoLanguages(paper, languages) {
    var circle = paper.circle(100, 100, 80);
}

function drawAppSchema(paper, languages) {
    if ("primary-language" in languages) {
        var primary_language = languages["primary-language"];
        drawAppSchemaWithOneLanguage(paper, primary_language);
    }
    else {
        drawAppSchemaWithTwoLanguages(paper, languages);
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

function onFrameworkAdd() {
    var listbox = document.getElementById("web_service_framework");
    var selected = listbox.options[listbox.selectedIndex].value;
    if (selected == null) {
        return;
    }

    var drawn = paper.drawn["web_service_framework"];
    if (drawn != null) {
        deleteDrawnObject(paper, "web_service_framework");
    }
    drawn = paper.drawn["web_service_framework"] = [];
    drawn.push(paper.rect(paper.width/2 - 64, 100, 128, 96).attr("stroke", "#088"));
    drawn.push(paper.text(paper.width/2, 148, selected).attr("font-size", 16));
}

function onFrameworkRemove() {
    deleteDrawnObject(paper, "web_service_framework");
}

function onAddApplicationPart(language, configuration, drop_down_id) {
    //alert("ADD");
    //alert(language);
    //alert(configuration);
    //alert(drop_down_id);
    if (configuration == "Web service framework") {
        onFrameworkAdd();
    }
}

function onRemoveApplicationPart(language, configuration, drop_down_id) {
    //alert("REMOVE");
    //alert(language);
    //alert(configuration);
    //alert(drop_down_id);
    if (configuration == "Web service framework") {
        onFrameworkRemove();
    }
}
