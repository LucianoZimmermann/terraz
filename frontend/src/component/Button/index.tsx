interface ButtonProps {
  content: string;
  onClick: () => void;
}

export default function Button({ content, onClick }: ButtonProps) {
  return (
    <button className="entityButton" onClick={onClick}>
      {content}
    </button>
  );
}
