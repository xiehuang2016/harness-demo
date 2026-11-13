param([switch]$SkipTests)
$ErrorActionPreference = 'Stop'
if ($SkipTests) { mvn -B -DskipTests package } else { mvn -B test }
if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }
