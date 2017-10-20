function DownloadJava($destination) {    
    if (Test-Path $destination) {
        return
    }
    $JDK_VER = "9"
    $JDK_FULL_VER="9+181"
    $source = "http://download.oracle.com/otn-pub/java/jdk/9.0.1+11/jdk-9.0.1_windows-x64_bin.exe"
    $cookie = New-Object System.Net.Cookie("oraclelicense", "accept-securebackup-cookie", "/", ".oracle.com")
    $session = New-Object Microsoft.PowerShell.Commands.WebRequestSession
    $session.Cookies.Add("http://www.oracle.com/", $cookie);
    Invoke-WebRequest -Uri $source -WebSession $session -OutFile $destination
}

function DownloadEclipse($destination) {
    $source = "http://mirror.onet.pl/pub/mirrors/eclipse//technology/epp/downloads/release/oxygen/1a/eclipse-java-oxygen-1a-win32-x86_64.zip"
    Download $source $destination    
}

function Download($source, $destination) {
    if (Test-Path $destination) {
        return
    }
    Invoke-WebRequest -Uri $source -OutFile $destination
}

function CreateFolder($path) {
    if (Test-Path $path) {
        return
    }
    New-Item $path -Force -ItemType Directory
}

function Run {
    param(
        [string] $FilePath,
        [switch] $Background
    )
    $params = @{
        FilePath = $FilePath
    }
    if ($Background) {
        $params.WindowStyle = "Minimized"
    }
    else {
        $params.Wait = $true
        $params.NoNewWindow = $true
    }
    if ($args.Length -gt 0) {
        $params.ArgumentList = $args
    }

    $p = Start-Process -PassThru @params

    $failed = if ($Background) {
        $p.HasExited
    } else {
        $p.ExitCode -gt 0
    }
    if ($failed) {
        throw "Process $FilePath exited with code $($p.ExitCode)"
    }
}

$installDir = Join-Path $PSScriptRoot "../downloads" 
$installersDir = Join-Path $installDir installers

$javaSetup = Join-Path $installersDir "java.exe"
$eclipseSetup = Join-Path $installersDir "eclipse.zip"

$eclipseDir = $installDir

CreateFolder $installDir
CreateFolder $installersDir

Write-Host "Download Java"
DownloadJava $javaSetup
Write-Host "Download Eclipse"
DownloadEclipse $eclipseSetup
Write-Host "Extract Eclipse"
Expand-Archive -Path $eclipseSetup -DestinationPath $eclipseDir -Force
Write-Host "Setup Java"
Run $javaSetup
Run (Join-Path $eclipseDir eclipse.exe)