# iniregex
ini file parser written in java using regex

## Program Output
```
CORRECTINI TEST
valid: true
section list: infosection, demosection, 
key list in [demosection]: prop2, prop1, foo, 
prop1 key value: he llo/asd
object toString:
infosection
┣totalfiles:100
┗filelength:20
demosection
┣prop2:worl d/qwe
┣prop1:he llo/asd
┗foo:bar

LONGWRONGINI TEST
line 'owner=root ;will change later' is not valid
valid: false

MULTIPLEWRONGINI TEST
line 'command=help [permissions]' is not valid
line 'moderator=alice,bob' is not valid
valid: false

COMPRESSEDINI TEST
valid: true
section list: github, gitlab, 
key list in [gitlab]: setupsteps, type, 
type key value: opensource
object toString:
github
┣owner:Microsoft
┣forks:20
┗branch:master
gitlab
┣setupsteps:8
┗type:opensource

WINDOWSINI TEST
valid: true
section list: boot loader, operating systems, 
key list in [operating systems]: edition, 
edition key value: Microsoft Windows XP Professional
object toString:
boot loader
┣default:multi(0)disk(0)rdisk(0)partition(1)WINDOWS
┗timeout:30
operating systems
┗edition:Microsoft Windows XP Professional
```
