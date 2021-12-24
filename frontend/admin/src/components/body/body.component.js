import React from "react";
import { connect } from "react-redux";
import { selectCurrentAction } from "../../redux/current-state/current-state.selectors";
import NewSwedishWord from "../new-word/new-swedish-word/new-swedish-word.component";
import NewEnglishWord from "../new-word/new-english-word/new-english-word.component";
import NewSpanishWord from "../new-word/new-spanish-word/new-spanish-word.component";

import "./body.styles.scss";

class Body extends React.Component {
  initialDisplay() {
    return (
      <div>
        <p>Sök här</p>
        <p>Skapa nytt svenskt ord</p>
        <p>Skapa nytt engelskt ord</p>
        <p>Skapa nytt spanskt ord</p>
        <p>LOGGA UT</p>
      </div>
    );
  }

  addSwedishWord() {
    return (
      <div className="body-main">
        <div className="new-words-container">
          <NewSwedishWord></NewSwedishWord>
          <NewEnglishWord></NewEnglishWord>
          <NewSpanishWord></NewSpanishWord>
        </div>
        <button onClick={this.submitNewWord}>Submit</button>
      </div>
    );
  }

  submitNewWord = async () => {
    await fetch(`/admin/api/words`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        ...this.props.swedishWord,
        language: "SWEDISH",
        translatedLanguages: {
          ENGLISH: {
            propagate: this.props.englishWord.propagate,
            translations: [{ word: this.props.englishWord.word }],
          },
          SPANISH: {
            propagate: this.props.spanishWord.propagate,
            translations: [{ word: this.props.spanishWord.word }],
          },
        },
      }),
    });
  };

  render() {
    const { currentAction } = this.props;
    console.log("ASD", this.props);

    return currentAction.name === "ADDING_SWEDISH_WORD"
      ? this.addSwedishWord()
      : this.initialDisplay();
  }
}

const mapStateToProps = (state) => ({
  currentAction: selectCurrentAction(state),
  swedishWord: state.swedishWord,
  englishWord: state.englishWord,
  spanishWord: state.spanishWord,
});

export default connect(mapStateToProps, null)(Body);
