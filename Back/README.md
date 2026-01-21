Faire un .env

Pour exécuter dans un powershell :

```powershell
Get-Content .env | ForEach-Object {
    if ($_ -and $_ -notmatch '^#') {
        $name, $value = $_ -split '=', 2
        [System.Environment]::SetEnvironmentVariable($name, $value, "Process")
    }
}
mvn spring-boot:run
```