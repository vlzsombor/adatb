[33mcommit a0c59b9d107f5ec79a986e5cdd9d0557094184fd[m[33m ([m[1;36mHEAD -> [m[1;32mdevelopment[m[33m, [m[1;31morigin/frontend[m[33m, [m[1;31morigin/development[m[33m)[m
Author: Veres-Lakos Zsombor <vl.zsombor@gmail.com>
Date:   Fri Apr 23 21:21:47 2021 +0200

    working version

[1mdiff --git a/.gitignore b/.gitignore[m
[1mindex 549e00a..612d3dd 100644[m
[1m--- a/.gitignore[m
[1m+++ b/.gitignore[m
[36m@@ -31,3 +31,4 @@[m [mbuild/[m
 [m
 ### VS Code ###[m
 .vscode/[m
[32m+[m[32msrc/main/resources/kepek[m
[1mdiff --git a/src/main/java/hu/szte/fenykepalbumok/controller/KepController.java b/src/main/java/hu/szte/fenykepalbumok/controller/KepController.java[m
[1mindex 7122751..4860877 100644[m
[1m--- a/src/main/java/hu/szte/fenykepalbumok/controller/KepController.java[m
[1m+++ b/src/main/java/hu/szte/fenykepalbumok/controller/KepController.java[m
[36m@@ -103,8 +103,6 @@[m [mpublic class KepController {[m
         kep.setPaths(uploadDir);[m
         savePhoto(multipartFile,uploadDir,kep);[m
 [m
[31m-[m
[31m-[m
         kepRepository.save(kep);[m
         return "redirect:/testModel";[m
     }[m
[1mdiff --git a/src/main/java/hu/szte/fenykepalbumok/db/DbInit.java b/src/main/java/hu/szte/fenykepalbumok/db/DbInit.java[m
[1mindex 49e531a..5c5e873 100644[m
[1m--- a/src/main/java/hu/szte/fenykepalbumok/db/DbInit.java[m
[1m+++ b/src/main/java/hu/szte/fenykepalbumok/db/DbInit.java[m
[36m@@ -33,7 +33,7 @@[m [mpublic class DbInit implements CommandLineRunner {[m
     private String ddl;[m
     @Override[m
     public void run(String... args) {[m
[31m-        //defaultDatabase();[m
[32m+[m[32m        defaultDatabase();[m
     }[m
 [m
     private void defaultDatabase(){[m
[36m@@ -55,7 +55,7 @@[m [mpublic class DbInit implements CommandLineRunner {[m
 [m
 [m
         Kategoria kategoria = new Kategoria(KategoriaEnum.TERMESZET_FOTOK.toString(), "ezek itten termeszet fotok");[m
[31m-        Kategoria kategoria2 = new Kategoria("TERMESZET_FOTOK2", "ezek itten termeszet fotok2");[m
[32m+[m[32m        Kategoria kategoria2 = new Kategoria(KategoriaEnum.TERMESZET_FOTOK2.toString(), "ezek itten termeszet fotok2");[m
 [m
         kategoriaRepository.save(kategoria);[m
         kategoriaRepository.save(kategoria2);[m
[1mdiff --git a/src/main/java/hu/szte/fenykepalbumok/model/Megye.java b/src/main/java/hu/szte/fenykepalbumok/model/Megye.java[m
[1mnew file mode 100644[m
[1mindex 0000000..232eb23[m
[1m--- /dev/null[m
[1m+++ b/src/main/java/hu/szte/fenykepalbumok/model/Megye.java[m
[36m@@ -0,0 +1,49 @@[m
[32m+[m[32mpackage hu.szte.fenykepalbumok.model;[m
[32m+[m
[32m+[m[32mimport javax.persistence.Entity;[m
[32m+[m[32mimport javax.persistence.Id;[m
[32m+[m[32mimport javax.persistence.JoinColumn;[m
[32m+[m[32mimport javax.persistence.ManyToOne;[m
[32m+[m[32mimport hu.szte.fenykepalbumok.model.Orszag;[m
[32m+[m
[32m+[m[32m@Entity[m
[32m+[m[32mpublic class Megye {[m
[32m+[m
[32m+[m[32m    @Id[m
[32m+[m[32m    private Long id;[m
[32m+[m
[32m+[m[32m    private String megye;[m
[32m+[m
[32m+[m[32m    @ManyToOne(optional = false)[m
[32m+[m[32m    @JoinColumn(name = "fk_orszag")[m
[32m+[m[32m    private Orszag orszag;[m
[32m+[m
[32m+[m[32m    public Orszag getOrszag() {[m
[32m+[m[32m        return orszag;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public void setOrszag(Orszag orszag) {[m
[32m+[m[32m        this.orszag = orszag;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public String getMegye() {[m
[32m+[m[32m        return megye;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public void setMegye(String megye) {[m
[32m+[m[32m        this.megye = megye;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m
[32m+[m[32m    public void setId(Long id) {[m
[32m+[m[32m        this.id = id;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m
[32m+[m[32m    public Long getId() {[m
[32m+[m[32m        return id;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m
[32m+[m[32m}[m
[32m+[m
[1mdiff --git a/src/main/java/hu/szte/fenykepalbumok/model/Orszag.java b/src/main/java/hu/szte/fenykepalbumok/model/Orszag.java[m
[1mnew file mode 100644[m
[1mindex 0000000..379f13a[m
[1m--- /dev/null[m
[1m+++ b/src/main/java/hu/szte/fenykepalbumok/model/Orszag.java[m
[36m@@ -0,0 +1,47 @@[m
[32m+[m[32mpackage hu.szte.fenykepalbumok.model;[m
[32m+[m
[32m+[m[32mimport javax.persistence.CascadeType;[m
[32m+[m[32mimport javax.persistence.Entity;[m
[32m+[m[32mimport javax.persistence.Id;[m
[32m+[m[32mimport javax.persistence.OneToMany;[m
[32m+[m[32mimport java.util.ArrayList;[m
[32m+[m[32mimport java.util.List;[m
[32m+[m
[32m+[m[32m@Entity[m
[32m+[m[32mpublic class Orszag {[m
[32m+[m[32m    @Id[m
[32m+[m[32m    private Long id;[m
[32m+[m
[32m+[m
[32m+[m[32m    @OneToMany(mappedBy = "orszag")[m
[32m+[m[32m    private List<Megye> megyek;[m
[32m+[m
[32m+[m[32m    public List<Megye> getMegyek() {[m
[32m+[m[32m        return megyek;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public void setMegyek(List<Megye> megyek) {[m
[32m+[m[32m        this.megyek = megyek;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public String getMegnevezes() {[m
[32m+[m[32m        return megnevezes;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public void setMegnevezes(String megnevezes) {[m
[32m+[m[32m        this.megnevezes = megnevezes;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public void setId(Long id) {[m
[32m+[m[32m        this.id = id;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m
[32m+[m[32m    public Long getId() {[m
[32m+[m[32m        return id;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    private String megnevezes;[m
[32m+[m
[32m+[m
[32m+[m[32m}[m
[1mdiff --git a/src/main/java/hu/szte/fenykepalbumok/model/Varos.java b/src/main/java/hu/szte/fenykepalbumok/model/Varos.java[m
[1mnew file mode 100644[m
[1mindex 0000000..843894a[m
[1m--- /dev/null[m
[1m+++ b/src/main/java/hu/szte/fenykepalbumok/model/Varos.java[m
[36m@@ -0,0 +1,22 @@[m
[32m+[m[32mpackage hu.szte.fenykepalbumok.model;[m
[32m+[m
[32m+[m[32mimport javax.persistence.Entity;[m
[32m+[m[32mimport javax.persistence.Id;[m
[32m+[m
[32m+[m[32m@Entity[m
[32m+[m[32mpublic class Varos {[m
[32m+[m
[32m+[m[32m    @Id[m
[32m+[m[32m    private Long id;[m
[32m+[m
[32m+[m[32m    private String varos;[m
[32m+[m
[32m+[m
[32m+[m[32m    public void setId(Long id) {[m
[32m+[m[32m        this.id = id;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public Long getId() {[m
[32m+[m[32m        return id;[m
[32m+[m[32m    }[m
[32m+[m[32m}[m
\ No newline at end of file[m
[1mdiff --git a/src/main/resources/application.properties b/src/main/resources/application.properties[m
[1mindex 5589786..73c6274 100644[m
[1m--- a/src/main/resources/application.properties[m
[1m+++ b/src/main/resources/application.properties[m
[36m@@ -59,7 +59,7 @@[m [mspring.datasource.password = adatb[m
 [m
 spring.datasource.driver.class = oracle.jdbc.driver.OracleDriver[m
 spring.jpa.database-platform=org.hibernate.dialect.Oracle10gDialect[m
[31m-spring.jpa.hibernate.ddl-auto = update[m
[32m+[m[32mspring.jpa.hibernate.ddl-auto = create[m
 [m
 spring.jpa.show-sql=true[m
 [m
