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

function onSelectDeploymentTypeIcon(deploymentType) {
    var id = "deployment-type" + "-" + deploymentType;
    var element = document.getElementById(id);
    if (element != null) {
        element.checked = true;
        enableNextButton();
    }
}

function readDropDownValue(drop_down_id) {
    var drop_down = document.getElementById(drop_down_id);
    return drop_down.options[drop_down.selectedIndex].value;
}

function wrapLongString(str, max) {
    if (str.length > 12) {
        var i = str.indexOf(" ", 12);
        if (i > -1) {
            var s1 = str.substring(0, i).trim();
            var s2 = str.substring(i).trim();
            return s1 + "\n" + s2;
        }
    }
    return str;
}

// drawing-related part

var MAX_DATABASES = 7;
var MAX_INTERFACES = 8;
var MAX_LIBRARIES = 8;

// global (well...) variables used by drawing part
var paper = null;
var databases = [];
var interfaces = [];
var libraries = [];

function createPaper(width, height) {
    paper = new Raphael(document.getElementById('canvas_container'), width, height);
    paper.drawn = {};
}

function drawInternetIcon(paper) {
    var image = "icons/internet.png";
    paper.image(image, paper.width/2 - 105/2, 10, 105, 105);
    paper.text(paper.width/2, 60, "Internet").attr("font-size", 16).attr("fill", "white");
}

function drawDatabaseIcon(paper, i, j, x, y, database_name) {
    var attributes1 = {};
    var attributes2 = {};
    if (database_name != null) {
        attributes1 = {"stroke": "black", "fill": "#ffc0c0"};
        attributes2 = {"stroke": "#ffc0c0", "fill": "#ffc0c0"};
    }
    else {
        attributes1 = {"stroke": "gray", "stroke-dasharray": "--"};
        attributes2 = {"fill": "white", "stroke": "white"};
    }
    drawn = paper.drawn["database_" + i] = [];
    drawn.push(paper.ellipse(x, y + 80, 40, 20).attr(attributes1));
    drawn.push(paper.rect(x - 40, y, 80, 80).attr("fill", "white").attr(attributes2));
    drawn.push(paper.path(["M", x - 40, y, "L", x - 40, y + 80]).attr(attributes1));
    drawn.push(paper.path(["M", x + 40, y, "L", x + 40, y + 80]).attr(attributes1));
    drawn.push(paper.ellipse(x, y, 40, 20).attr("fill", "white").attr(attributes1));

    if (database_name != null) {
        // database name
        drawn.push(paper.text(x, y + 120, database_name).attr("font-size", 12).attr("fill", "black"));
        // connection lines
        var offset = j - (MAX_DATABASES >> 1);
        var yn = y - 40 - Math.abs(offset) * 15;
        var xn = paper.width/2 + offset * 10;
        drawn.push(paper.path(["M", x, y, "L", x, yn, "L", xn, yn, "L", xn, paper.height/2 + 64 + 1]).attr("stroke", "#800000"));
    }
}

function drawInterfaceIcon(paper, i, x, y, interface_name) {
    var attributes1 = {};
    var attributes2 = {};
    if (interface_name != null) {
        attributes1 = {"stroke": "black", "fill": "#c0c0ff"};
        attributes2 = {"stroke": "#c0c0ff", "fill": "#c0c0ff"};
    }
    else {
        attributes1 = {"stroke": "gray", "stroke-dasharray": "--", "stroke-width": 1};
        attributes2 = {"fill": "white", "stroke": "gray"};
    }
    drawn = paper.drawn["interface_" + i] = [];
    drawn.push(paper.rect(x+3, y+3, 90, 40).attr("fill", "white").attr(attributes1));
    drawn.push(paper.rect(x, y, 90, 40).attr("fill", "white").attr(attributes1));

    if (interface_name != null) {
        // interface name
        drawn.push(paper.text(x + 40, y + 20, interface_name).attr("font-size", 12).attr("fill", "black"));
        // connection lines
        var x1 = x + 90
        var y1 = y + 20
        var x2 = x + 90 + 100 - i * 10;
        var y2 = paper.height/2 - 40 + i * 10
        var x3 = paper.width/2 - 66;
        drawn.push(paper.path(["M", x1, y1, "H", x2, "V", y2, "H", x3]).attr("stroke", "#800000").attr("stroke-width", "2").attr("arrow-start", "block-wide-long").attr("arrow-end", "block-wide-long"));
    }
}

function drawLibraryIcon(paper, i, x, y, library_name) {
    console.log(i, x, y, library_name);
    var attributes1 = {};
    var attributes2 = {};
    if (library_name != null) {
        attributes1 = {"stroke": "black", "fill": "#c0ffc0"};
        attributes2 = {"stroke": "#c0c0ff", "fill": "#c0ffc0"};
    }
    else {
        attributes1 = {"stroke": "gray", "stroke-dasharray": "--", "stroke-width": 1};
        attributes2 = {"fill": "white", "stroke": "gray"};
    }
    drawn = paper.drawn["library_" + i] = [];
    drawn.push(paper.rect(x+3, y+3, 90, 40).attr("fill", "white").attr(attributes1));
    drawn.push(paper.rect(x, y, 90, 40).attr("fill", "white").attr(attributes1));

    if (library_name != null) {
        // interface name
        drawn.push(paper.text(x + 40, y + 20, library_name).attr("font-size", 12).attr("fill", "black"));
    }
}

function drawDatabaseIcons(paper, databases) {
    var i;
    for (i=0; i < MAX_DATABASES; i++) {
        var permut = [3, 2, 4, 1, 5, 0, 6];
        var x = 50 + permut[i] * 90;
        var database_name = null;
        if (databases.length > i) {
            database_name = databases[i];
        }
        drawDatabaseIcon(paper, i, permut[i], x, 500, database_name);
    }
}

function drawInterfacesIcons(paper, interfaces) {
    var i;
    for (i=0; i < MAX_INTERFACES; i++) {
        var y = 20 + i * 50;
        var interface_name = null;
        if (interfaces.length > i) {
            interface_name = interfaces[i];
        }
        drawInterfaceIcon(paper, i, 10, y, interface_name);
    }
}

function drawLibraryIcons(paper, libraries) {
    var i;
    for (i=0; i < MAX_LIBRARIES; i++) {
        var y = 20 + (i>>1) * 50;
        if (i % 2 == 0) {
            var x = paper.width - 200;
        }
        else {
            var x = paper.width - 100;
        }
        var library_name = null;
        if (libraries.length > i) {
            library_name = libraries[i];
        }
        drawLibraryIcon(paper, i, x, y, library_name);
    }
}

function deleteIconArray(paper, prefix, max_index) {
    var i;
    for (i=0; i < max_index; i++) {
        deleteDrawnObject(paper, prefix + "_" + i);
    }
}

function deleteDatabaseIcons(paper) {
    deleteIconArray(paper, "database", MAX_DATABASES);
}


function deleteInterfacesIcons(paper) {
    deleteIconArray(paper, "interface", MAX_INTERFACES);
}

function deleteLibraryIcons(paper) {
    deleteIconArray(paper, "library", MAX_LIBRARIES);
}

function drawAppSchemaWithOneLanguage(paper, app_type, language) {
    var image = "languages/128x128/" + language + ".png";
    paper.image(image, paper.width/2 - 64, paper.height/2 - 64, 128, 128);
    paper.rect(paper.width/2 - 64, paper.height/2 - 64, 128, 128).attr("stroke", "#088");
    if (app_type == "microservice") {
        drawInternetIcon(paper);
        drawDatabaseIcons(paper, databases);
        drawInterfacesIcons(paper, interfaces);
        drawLibraryIcons(paper, interfaces);
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

function redrawDatabaseIcons(paper, databases) {
    deleteDatabaseIcons(paper);
    drawDatabaseIcons(paper, databases);
}

function redrawInterfaceIcons(paper, interfaces) {
    deleteInterfacesIcons(paper);
    drawInterfacesIcons(paper, interfaces);
}

function redrawLibraryIcons(paper, interfaces) {
    deleteLibraryIcons(paper);
    drawLibraryIcons(paper, interfaces);
}

function deleteDrawnObject(paper, selector) {
    if (selector in paper.drawn) {
        var to_delete = paper.drawn[selector];
        var i;
        for (i=0; i < to_delete.length; i++) {
            to_delete[i].remove();
        }
        paper.drawn[selector] = null;
    }
}

// event handlers

function onFrameworkAdd(value) {
    if (value == null) {
        return;
    }

    var drawn = paper.drawn["web_service_framework"];
    if (drawn != null) {
        deleteDrawnObject(paper, "web_service_framework");
    }
    drawn = paper.drawn["web_service_framework"] = [];
    drawn.push(paper.path("M320,256L320,92").attr("stroke-dasharray", "--").attr("stroke", "gray").attr("stroke-width", "2").attr("arrow-end", "block-wide-long"));
    drawn.push(paper.rect(paper.width/2 - 64, 140, 128, 64).attr("stroke", "#088").attr("fill", "white"));
    drawn.push(paper.text(paper.width/2, 172, value).attr("font-size", 16));
}

function onFrameworkRemove() {
    deleteDrawnObject(paper, "web_service_framework");
}

function onDatabaseAdd(value) {
    if (databases.indexOf(value) == -1) {
        if (databases.length < MAX_DATABASES) {
            databases.push(value);
            redrawDatabaseIcons(paper, databases);
        }
    }
}

function onDatabaseRemove(value) {
    var i = databases.indexOf(value);
    if (i > -1) {
        databases.splice(i, 1);
        redrawDatabaseIcons(paper, databases);
    }
}

function onInterfaceAdd(value) {
    if (interfaces.indexOf(value) == -1) {
        if (interfaces.length < MAX_INTERFACES) {
            interfaces.push(value);
            redrawInterfaceIcons(paper, interfaces);
        }
    }
}

function onInterfaceRemove(value) {
    var i = interfaces.indexOf(value);
    if (i > -1) {
        interfaces.splice(i, 1);
        redrawInterfaceIcons(paper, interfaces);
    }
}

function onLibraryAdd(value) {
    if (libraries.indexOf(value) == -1) {
        if (libraries.length < MAX_LIBRARIES) {
            libraries.push(value);
            redrawLibraryIcons(paper, libraries);
        }
    }
}

function onLibraryRemove(value) {
    var i = libraries.indexOf(value);
    if (i > -1) {
        libraries.splice(i, 1);
        redrawLibraryIcons(paper, libraries);
    }
}

function onQueueAdd(value) {
    if (value == null) {
        return;
    }
    var drawn = paper.drawn["queue"];
    if (drawn != null) {
        deleteDrawnObject(paper, "queue");
    }
    drawn = paper.drawn["queue"] = [];
    var ax1 = paper.width/2 + 64;
    var ax2 = paper.width - 138;
    var ay1 = 256 + 40;
    var ay2 = 256 + 88;
    drawn.push(paper.path(["M", ax1, ay1, "H", ax2]).attr("stroke", "gray").attr("stroke-width", "2").attr("arrow-end", "block-wide-long"));
    drawn.push(paper.path(["M", ax2, ay2, "H", ax1]).attr("stroke", "gray").attr("stroke-width", "2").attr("arrow-end", "block-wide-long"));
    switch (value) {
        case "Apache Kafka":
            var image = "icons/kafka.jpg";
            drawn.push(paper.image(image, paper.width - 138 + 32, 256+5, 64, 90));
            break;
        case "JetStream":
            var image = "icons/nats.png";
            drawn.push(paper.image(image, paper.width - 138 + 15, 256+25, 99, 48));
            break;
        case "Redis streams":
            var image = "icons/redis.png";
            drawn.push(paper.image(image, paper.width - 138 + 15, 256+5, 100, 94));
            break;
        default:
            var image = "icons/queue.png";
            drawn.push(paper.image(image, paper.width - 138 + 32, 256 + 20, 64, 42));
            break;
    }
    value = wrapLongString(value, 12);
    drawn.push(paper.rect(ax2, 256, 128, 128).attr("stroke", "#088"));
    drawn.push(paper.text(ax2 + 64, 256 + 108, value).attr("font-size", 10));
}

function onQueueRemove(value) {
    deleteDrawnObject(paper, "queue");
}

function onAddApplicationPart(language, configuration, drop_down_id) {
    console.log("command: ADD")
    console.log("language: " + language);
    console.log("config:   " + configuration);
    console.log("key:      " + drop_down_id);
    var value = readDropDownValue(drop_down_id);
    console.log("value:    " + value);

    // ignore special first value
    if (value == "(please choose)") {
        return;
    }

    switch (configuration) {
    case "Web service framework":
        onFrameworkAdd(value);
        break;
    case "Data storage":
        onDatabaseAdd(value);
        break;
    case "Message queuing service":
    case "Streaming platform":
        onQueueAdd(value);
        break;
    case "Other interfaces":
        onInterfaceAdd(value);
        break;
    case "Libraries":
        onLibraryAdd(value);
        break;
    }
}

function onRemoveApplicationPart(language, configuration, drop_down_id) {
    var value = readDropDownValue(drop_down_id);

    switch (configuration) {
    case "Web service framework":
        onFrameworkRemove();
        break;
    case "Data storage":
        onDatabaseRemove(value);
        break;
    case "Message queuing service":
    case "Streaming platform":
        onQueueRemove(value);
        break;
    case "Other interfaces":
        onInterfaceRemove(value);
        break;
    case "Libraries":
        onLibraryRemove(value);
        break;
    }
}
