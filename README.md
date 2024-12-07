# IPWA02-01
Dies ist der Code zur Fallstudie des Moduls IPWA02-01.

1. Die Anwendung muss im DocumentRoot eines Java-fähigen Webservers gespeichert und über diesen Webserver gestartet werden.
2. Zusätzlich muss eine MariaDB-Datenbank gehostet werden. Die Verbindungsdaten dazu sind in der Datei /GhostNetFishing/src/main/resources/META-INF/persistence.xml zu konfigurieren.
3. Beim Aufruf des Verzeichnisses im Browser wird standardmäßig die index.html geladen. Sollte dies nicht automatisch geschehen, kann sie manuell aufgerufen werden oder alternativ die Datei index.xhtml.
4. Beim erstmaligen Aufrufen nach einem Start oder Neustart der Anwendung werden die Datenbanktabellen automatisch erstellt und die Musterdaten eingepflegt.

> [!Tip]
> Bitte sicherstellen, dass der Webserver und die Datenbank korrekt konfiguriert sind, um einen reibungslosen Ablauf zu gewährleisten.