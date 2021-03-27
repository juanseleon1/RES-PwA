CURDIR=$(pwd)
ORADIR="$CURDIR/oracledb"
if [ ! -d "$ORADIR" ]; then
mkdir "$ORADIR"
chmod 777 "$ORADIR"
fi
docker run -d -p 1521:1521 -v "$ORADIR":/u01/app/oracle --name oracle-db store/oracle/database-enterprise:12.2.0.1
