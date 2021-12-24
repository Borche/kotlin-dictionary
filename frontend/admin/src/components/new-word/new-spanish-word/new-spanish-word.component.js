import React from "react";
import { connect } from "react-redux";

import {
  updateSpanishWord,
  updateSpanishPropagate,
} from "../../../redux/spanish-word/spanish-word.actions";

import "../new-word.styles.scss";

class NewSpanishWord extends React.Component {
  DEAULT_PROPAGATE = true;

  componentDidMount() {
    this.props.updatePropagate({ propagate: this.DEAULT_PROPAGATE });
  }

  updateWord = (event) => {
    this.props.updateWord({ word: event.target.value });
  };

  updateType = (event) => {
    this.props.updatePropagate({ propagate: this.DEAULT_PROPAGATE });
  };

  render() {
    return (
      <table>
        <tbody>
          <tr class="title-row">
            <td colspan="2">Spanish</td>
            <td></td>
          </tr>
          <tr>
            <td>
              <label htmlFor="swe-type">Propagate</label>
            </td>
            <td>
              <input type="checkbox" defaultChecked={this.DEAULT_PROPAGATE} />
            </td>
          </tr>
          <tr>
            <td>
              <label htmlFor="spa-word">Word</label>
            </td>
            <td>
              <input type="text" id="spa-word" onChange={this.updateWord} />
            </td>
          </tr>
        </tbody>
      </table>
    );
  }
}

const mapDispatchToProps = (dispatch) => ({
  updateWord: (obj) => dispatch(updateSpanishWord(obj)),
  updatePropagate: (obj) => dispatch(updateSpanishPropagate(obj)),
});

export default connect(null, mapDispatchToProps)(NewSpanishWord);
