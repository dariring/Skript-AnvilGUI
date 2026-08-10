# Skript-AnvilGUI

[![Downloads](https://img.shields.io/github/downloads/dariring/Skript-AnvilGUI/total)](https://github.com/dariring/Skript-AnvilGUI/releases)
[![Stats](https://bstats.org/signatures/bukkit/Skript-AnvilGUI.svg)](https://bstats.org/plugin/bukkit/Skript-AnvilGUI/16474)

A Skript Addon that allows users to create Anvil GUIs to read user input in Minecraft servers (Paper/Spigot 1.12 - 1.21.x / 26.x, Skript 2.15+).

---

## 📌 Original Repository & Credit (원본 링크)
- **Original Repository / 원본 저장소**: [DereWah/Skript-AnvilGUI](https://github.com/DereWah/Skript-AnvilGUI)
- **Original Author's Maintenance Fork / 원본 작성자 포크**: [Dere-Wah/Skript-AnvilGUI](https://github.com/Dere-Wah/Skript-AnvilGUI)
- **Current Repository / 현재 저장소 (Skript 2.15+ 호환 포크)**: [dariring/Skript-AnvilGUI](https://github.com/dariring/Skript-AnvilGUI)

---

## 🌐 Language / 언어 선택
- [English](#-english)
- [한국어](#-한국어)

---

<a name="-english"></a>
# 🇺🇸 English

## Overview
**Skript-AnvilGUI** is a Skript addon enabling server admins and developers to open Anvil GUIs to players and read their text input cleanly.

- **Supported Server Versions**: Paper / Spigot 1.12 up to 1.21.x (26.x)
- **Required Skript Version**: Skript 2.15+

## Tutorial & Documentation
- **In-depth Tutorial**: [SkriptHub Tutorial #77](https://skripthub.net/tutorials/77)
- **SkriptHub Docs**: [Skript-AnvilGUI Docs](http://skripthub.net/docs/?addon=Skript-AnvilGUI)

> [!IMPORTANT]
> **Syntax Changes for Skript 2.7+ & 2.15+**
> - In **Skript 2.7+**, use `virtual anvil` instead of `anvil gui` for event values (e.g., `event-virtual anvil`).
> - In **Skript 2.15+**, use `anvil gui title of %virtualanvil%` and `anvil gui text of %virtualanvil%`. 
>   *Reason*: Skript 2.15+ claims the generic `title of` and `text of` syntaxes internally.

---

## Syntax Reference

### 1. Effects
#### Open Anvil GUI
```skript
open (anvilgui|anvil gui) %virtualanvil% to %players%
```
Opens an Anvil GUI to the specified player(s). 
*Note*: Set up the slots and title/text before opening the GUI to the player.

### 2. Types
#### Virtual Anvil
```skript
%virtualanvil%
virtual anvil
event-virtual anvil
```
Represents an Anvil GUI object containing its title, default text, and items.

### 3. Events
#### Anvil GUI Close
```skript
on anvil gui close
```
Fires when an Anvil GUI created by this addon is closed.
- `event-text`: The text in the anvil input when closed.
- `event-virtual anvil`: The Anvil GUI object.

#### Anvil GUI Click
```skript
on anvil gui click
```
Fires when a player clicks a slot inside an Anvil GUI created by this addon.
- `event-integer`: Clicked slot index (0 = left, 1 = right, 2 = output).
- `event-text`: Current text input in the anvil.
- `event-virtual anvil`: The Anvil GUI object.

### 4. Expressions
#### New Anvil GUI
```skript
new anvil gui
[a] new anvil gui (named|with title) %string% with [default] text %string%
```

#### Anvil GUI Title
```skript
[the] anvil [gui] title of %virtualanvil%
%virtualanvil%'[s] anvil [gui] title
```

#### Anvil GUI Text
```skript
[the] anvil [gui] text of %virtualanvil%
%virtualanvil%'[s] anvil [gui] text
```

#### Anvil GUI Item Slots
```skript
(left|right|output) (item|slot) of %virtualanvil%
```

---

## Code Examples

### Basic Password Verification
```skript
command /password:
    trigger:
        set {_gui} to a new anvil gui named "&0Insert Password" with default text "password"
        open anvil gui {_gui} to player

on anvil gui click:
    if event-integer is 2: # Clicked the output slot
        if anvil gui title of event-virtual anvil is "&0Insert Password":
            if event-text is "1234":
                close player's inventory
                send "&aPassword correct!" to player
            else:
                set anvil gui text of event-virtual anvil to "Wrong password"
                open anvil gui event-virtual anvil to player
```

---

<a name="-한국어"></a>
# 🇰🇷 한국어

## 개요
**Skript-AnvilGUI**는 마인크래프트 서버에서 플레이어에게 모니(모망치) GUI를 열어 텍스트 입력을 받을 수 있게 해주는 Skript 애드온입니다.

- **지원 서버 버전**: Paper / Spigot 1.12 ~ 1.21.x (26.x)
- **필수 Skript 버전**: Skript 2.15+

## 튜토리얼 및 문서
- **상세 튜토리얼**: [SkriptHub Tutorial #77](https://skripthub.net/tutorials/77)
- **SkriptHub 공식 문서**: [Skript-AnvilGUI Docs](http://skripthub.net/docs/?addon=Skript-AnvilGUI)

> [!IMPORTANT]
> **Skript 2.7+ 및 2.15+ 구문 변경 사항**
> - **Skript 2.7+ 이상**: 이벤트 변수 사용 시 `anvil gui` 대신 `virtual anvil`을 사용하세요 (예: `event-virtual anvil`).
> - **Skript 2.15+ 이상**: 제목과 텍스트를 가져올 때 `anvil gui title of %virtualanvil%` 및 `anvil gui text of %virtualanvil%` 구문을 사용해야 합니다.
>   *이유*: Skript 2.15 버전부터 기본 `title of` 및 `text of` 구문을 Skript 자체에서 점유하므로, 모망치 GUI에서는 `anvil gui title` / `anvil gui text` 구문을 써야 정상 작동합니다.

---

## 구문 레퍼런스

### 1. 효과 (Effects)
#### Anvil GUI 열기
```skript
open (anvilgui|anvil gui) %virtualanvil% to %players%
```
지정한 플레이어에게 Anvil GUI를 엽니다.
*참고*: GUI를 열기 전에 제목, 기본 텍스트, 슬롯 아이템을 먼저 설정해야 합니다.

### 2. 타입 (Types)
#### Virtual Anvil
```skript
%virtualanvil%
virtual anvil
event-virtual anvil
```
Anvil GUI 객체 정보를 담고 있는 데이터 타입입니다.

### 3. 이벤트 (Events)
#### Anvil GUI 닫기 이벤트
```skript
on anvil gui close
```
본 애드온으로 생성된 Anvil GUI가 닫힐 때 발생합니다.
- `event-text`: GUI가 닫힐 당시 입력창에 작성되어 있던 텍스트
- `event-virtual anvil`: 해당 Anvil GUI 객체

#### Anvil GUI 클릭 이벤트
```skript
on anvil gui click
```
본 애드온으로 생성된 Anvil GUI 내부 슬롯을 클릭할 때 발생합니다.
- `event-integer`: 클릭한 슬롯 번호 (0 = 왼쪽, 1 = 오른쪽, 2 = 결과/출력 슬롯)
- `event-text`: 클릭 당시 입력창에 작성되어 있던 텍스트
- `event-virtual anvil`: 해당 Anvil GUI 객체

### 4. 표현식 (Expressions)
#### 새 Anvil GUI 생성
```skript
new anvil gui
[a] new anvil gui (named|with title) %string% with [default] text %string%
```

#### Anvil GUI 제목 (Title)
```skript
[the] anvil [gui] title of %virtualanvil%
%virtualanvil%'[s] anvil [gui] title
```

#### Anvil GUI 입력 텍스트 (Text)
```skript
[the] anvil [gui] text of %virtualanvil%
%virtualanvil%'[s] anvil [gui] text
```

#### Anvil GUI 아이템 슬롯 (Item Slots)
```skript
(left|right|output) (item|slot) of %virtualanvil%
```

---

## 예제 코드

### 비밀번호 입력 검증 예제
```skript
command /password:
    trigger:
        set {_gui} to a new anvil gui named "&0비밀번호 입력" with default text "password"
        open anvil gui {_gui} to player

on anvil gui click:
    if event-integer is 2: # 결과(출력) 슬롯 클릭 시
        if anvil gui title of event-virtual anvil is "&0비밀번호 입력":
            if event-text is "1234":
                close player's inventory
                send "&a비밀번호가 일치합니다!" to player
            else:
                set anvil gui text of event-virtual anvil to "비밀번호 틀림"
                open anvil gui event-virtual anvil to player # 변경된 텍스트로 다시 열기
```

---

## ❓ FAQ (자주 묻는 질문)
- **Q. 결과(Output) 슬롯의 아이템이 변경되지 않습니다.**
  - **A**: 모장의 기본 AnvilGUI 라이브러리 메커니즘상 결과 슬롯 아이템은 서버에서 재작성됩니다. 안전하게 클릭 이벤트를 감지하려면 왼쪽/오른쪽 슬롯 아이템을 설정하여 사용하세요.
