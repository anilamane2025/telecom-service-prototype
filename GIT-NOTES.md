# 🚀 Git Practical Notes (By Step Learning)

---

## 🔰 Basic Commands

```bash
git status
git add .
git commit -m "message"
git push
```

---

## 🌿 Branching

```bash
git branch
git checkout dev
git checkout -b feature-branch
```

Delete branch:

```bash
git branch -d branch-name
git push origin --delete branch-name
```

---

## 🔁 Merge

```bash
git checkout dev
git merge feature-branch
```

### Types of Merge

1. Fast-forward (NO conflict)
2. Three-way merge (Possible conflict)

---

## ⚡ Fast Forward Merge

Condition:

* No new commit in dev
* Feature branch is ahead

Result:

```text
dev directly moves forward
```

---

## 💣 Merge Conflict

### Conflict happens when:

* Same file
* Same method
* Same line changed
* Different commits in both branches

---

## 🔧 Conflict Resolution Steps

1. Open conflicted file
2. You will see:

```text
<<<<<<< HEAD
DEV CODE
=======
FEATURE CODE
>>>>>>> branch
```

3. Edit manually
4. Remove markers
5. Save file

Then:

```bash
git add .
git commit -m "resolved conflict"
```

---

## 🔍 Fetch vs Pull vs Merge

### git fetch

```bash
git fetch
```

* Gets latest from remote
* Does NOT change code

---

### git merge

```bash
git merge origin/dev
```

* Applies changes to local

---

### git pull

```bash
git pull
```

* fetch + merge

---

## 🧠 Golden Rule

```text
fetch = check
merge = apply
pull = auto apply
```

---

## 📦 Stash Commands

### Save work

```bash
git stash
```

### List stash

```bash
git stash list
```

### Apply stash

```bash
git stash pop
```

---

## 🔍 Check Local vs Remote

### Local file

```bash
type path\to\file
```

### Remote file

```bash
git show origin/dev:path/to/file
```

### Difference

```bash
git diff origin/dev -- file
```

---

## 📜 Logs

```bash
git log --oneline
git log --oneline --graph --all
```

---

## 🔥 Real Conflict Practice Summary

1. Change in dev
2. Commit
3. Switch to feature
4. Change same line differently
5. Commit
6. Switch to dev
7. Merge → conflict

---

## 🎯 Important Tips

* Always check branch before commit
* Use fetch before pull in real projects
* Conflict is NORMAL, not error
* Practice same-file same-line changes

---

## 🏁 Final Line

```text
Git samajh gaya = Developer strong 💪
```

---

(Keep this file for revision anytime 🚀)